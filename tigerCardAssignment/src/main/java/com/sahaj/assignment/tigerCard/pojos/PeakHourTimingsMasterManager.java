package com.sahaj.assignment.tigerCard.pojos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum PeakHourTimingsMasterManager {
	
	
	INSTANCE;
	
	public static final int PEAK_HOUR_TIMING_DAY_CATEGORY_INDEX = 0;
	public static final int PEAK_HOUR_TIMING_START_TIME_INDEX = 1;
	public static final int PEAK_HOUR_TIMING_END_TIME_INDEX = 2;
	
	
	
	List<PeakHourTimings> weekDayPeakHourTimings = new ArrayList<PeakHourTimings>();
	List<PeakHourTimings> weekendPeakHourTimings = new ArrayList<PeakHourTimings>();
	
	
	public void addWeekDayPeakHourTimings(PeakHourTimings peakHourTiming)
	{
		this.weekDayPeakHourTimings.add(peakHourTiming);
	}
	
	
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			isWeekDayCategoryData = index == PEAK_HOUR_TIMING_DAY_CATEGORY_INDEX && peakHourData[index].equals("weekday") ? true:isWeekDayCategoryData;
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
