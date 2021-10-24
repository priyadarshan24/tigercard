package com.sahaj.assignment.tigerCard.pojos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Leverage Singleton Pattern to manage the Peak Hour timing Master Data
 * Change Value to Reference technique
 * Allows querying the Master Data Repository & updating the master data repo
 * 
 * @author pd
 *
 */
public enum PeakHourTimingsMasterManager {
	
	
	INSTANCE;
	
	private static final int PEAK_HOUR_TIMING_DAY_CATEGORY_INDEX = 0;
	private static final int PEAK_HOUR_TIMING_START_TIME_INDEX = 1;
	private static final int PEAK_HOUR_TIMING_END_TIME_INDEX = 2;
	private static final String WEEKDAY_LITERAL = "weekday";
	
	
	private List<PeakHourTimings> weekDayPeakHourTimings = new ArrayList<PeakHourTimings>();
	private List<PeakHourTimings> weekendPeakHourTimings = new ArrayList<PeakHourTimings>();
	
	
	//Encapsulate collection
	public void addWeekDayPeakHourTimings(PeakHourTimings peakHourTiming)
	{
		this.weekDayPeakHourTimings.add(peakHourTiming);
	}
	
	
	//Encapsulate collection
	public List<PeakHourTimings> getWeekDayPeakHourTimings()
	{
		return Collections.unmodifiableList(weekDayPeakHourTimings);
	} 
	
	public void addWeekendPeakHourTimings(PeakHourTimings peakHourTiming)
	{
		this.weekendPeakHourTimings.add(peakHourTiming);
	}
	
	
	public List<PeakHourTimings> getWeekendPeakHourTimings()
	{
		return Collections.unmodifiableList(weekendPeakHourTimings);
	} 
	
	
	public void initializePeakHourTimingMaster(String inputFileFolder, String fileName)
	{
		
		File peakHourTimingFile = new File(inputFileFolder, fileName);
		int lineCount = 0;
		String currentLineRead = null;
		
		try (BufferedReader bufferedFileReader = new BufferedReader(new FileReader(peakHourTimingFile)) ){
			
			
			while((currentLineRead = bufferedFileReader.readLine()) != null)
			{
				if( lineCount == 0)
				{
					lineCount++;
					continue;
				}
				
				String[] peakHourData = currentLineRead.split(",");
				
				validateInput(peakHourData);
				
				createPeakHourTimingsMaster(peakHourData);
				
			}
			
			
			
		} catch (IOException e) {
			
			System.out.println("Exception occured while initializing the Peak hour Timings Master Data " + e.getMessage() );
		} 
	}



	private void validateInput(String[] peakHourData) {
		if( peakHourData.length < 3)
		{
			throw new IllegalArgumentException("Wrong Input format type for Peak Hour Master Data");
		}
	}
	
	
	private void createPeakHourTimingsMaster(String[] peakHourData)
	{
		boolean isWeekDayCategoryData = false;
		String peakHourStart = null;
		String peakHourEnd = null;
		
		for(int index = 0; index <peakHourData.length; index++)
		{	
			isWeekDayCategoryData = index == PEAK_HOUR_TIMING_DAY_CATEGORY_INDEX && peakHourData[index].equalsIgnoreCase(WEEKDAY_LITERAL) ? true:isWeekDayCategoryData;
			peakHourStart = index == PEAK_HOUR_TIMING_START_TIME_INDEX ? peakHourData[index] : peakHourStart;
			peakHourEnd = index == PEAK_HOUR_TIMING_END_TIME_INDEX ? peakHourData[index] : peakHourEnd;
		
		}
		
		PeakHourTimings peakHourTimings = new PeakHourTimings(peakHourStart, peakHourEnd);
		
		if( isWeekDayCategoryData)
		{
			PeakHourTimingsMasterManager.INSTANCE.addWeekDayPeakHourTimings(peakHourTimings);
		}
		else
		{
			PeakHourTimingsMasterManager.INSTANCE.addWeekendPeakHourTimings(peakHourTimings);
		}
	}

}
