package com.sahaj.assignment.tigerCard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimings;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimingsMasterManager;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;

public class RunTigerCardApp {

	
	public static final int PEAK_HOUR_TIMING_DAY_CATEGORY_INDEX = 0;
	public static final int PEAK_HOUR_TIMING_START_TIME_INDEX = 1;
	public static final int PEAK_HOUR_TIMING_END_TIME_INDEX = 2;
	
	
	//fromZone,toZone,peakHourFare,offPeakHourFare,dailyCap,weeklyCap
	public static final int ZONE_MASTER_FROM_ZONE_INDEX = 0;
	public static final int ZONE_MASTER_TO_ZONE_INDEX = 1;
	public static final int ZONE_MASTER_PEAK_HOUR_FARE_INDEX = 2;
	public static final int ZONE_MASTER_OFF_PEAK_HOUR_INDEX = 3;
	public static final int ZONE_MASTER_DAILY_CAP_INDEX = 4;
	public static final int ZONE_MASTER_WEEKLY_CAP_INDEX = 5;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String pathSeperator = System.getProperty("file.separator");
		String inputFileFolder = System.getProperty("user.dir") + pathSeperator + "input" + pathSeperator;
		
		
		System.out.println(inputFileFolder);
		
		RunTigerCardApp runTigerCardApp = new RunTigerCardApp();
		runTigerCardApp.readPeakHourTimings(inputFileFolder, "peakHourMaster.txt");
		runTigerCardApp.readZoneFareMasterData(inputFileFolder, "zoneFareMaster.txt");
		
		Object instance1 = PeakHourTimingsMasterManager.INSTANCE;
		Object instance2 = ZoneTravelMasterManager.INSTANCE;
		System.out.println("Print peakHour:" + PeakHourTimingsMasterManager.INSTANCE);
		System.out.println("Print zoneFareMaster:" + ZoneTravelMasterManager.INSTANCE);
		
	}
	
	
	public void readPeakHourTimings(String inputFileFolder, String fileName)
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
				
				if( peakHourData.length < 3)
				{
					throw new IllegalArgumentException("Wrong Input format type for Peak Hour Master Data");
				}
				
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
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public void readZoneFareMasterData(String inputFileFolder, String fileName)
	{
		
		File zoneFareMasterDataFile = new File(inputFileFolder, fileName);
		int lineCount = 0;
		String currentLineRead = null;
		
		try (BufferedReader bufferedFileReader = new BufferedReader(new FileReader(zoneFareMasterDataFile)) ){
			
			
			while((currentLineRead = bufferedFileReader.readLine()) != null)
			{
				if( lineCount == 0)
				{
					lineCount++;
					continue;
				}
				
				String[] zoneFareMasterData = currentLineRead.split(",");
				
				if( zoneFareMasterData.length < 5)
				{
					throw new IllegalArgumentException("Wrong Input format type for Zone Fare Master Data");
				}
				
				String fromZone = null, toZone= null;
				double peakHourFare = 0, offPeakHourFare = 0, dailyCapFare = 0, weeklyCapFare = 0;
				
				
				for(int index = 0; index <zoneFareMasterData.length; index++)
				{	
					fromZone = index == ZONE_MASTER_FROM_ZONE_INDEX ? zoneFareMasterData[ZONE_MASTER_FROM_ZONE_INDEX] : fromZone;
					toZone = index == ZONE_MASTER_TO_ZONE_INDEX ? zoneFareMasterData[ZONE_MASTER_TO_ZONE_INDEX] :toZone;
					peakHourFare = index == ZONE_MASTER_PEAK_HOUR_FARE_INDEX ? Double.parseDouble(zoneFareMasterData[ZONE_MASTER_PEAK_HOUR_FARE_INDEX]) : peakHourFare;
					offPeakHourFare = index == ZONE_MASTER_OFF_PEAK_HOUR_INDEX ? Double.parseDouble(zoneFareMasterData[ZONE_MASTER_OFF_PEAK_HOUR_INDEX]) : offPeakHourFare;
					dailyCapFare = index == ZONE_MASTER_DAILY_CAP_INDEX ? Double.parseDouble(zoneFareMasterData[ZONE_MASTER_DAILY_CAP_INDEX]) : dailyCapFare;
					weeklyCapFare = index == ZONE_MASTER_WEEKLY_CAP_INDEX ? Double.parseDouble(zoneFareMasterData[ZONE_MASTER_WEEKLY_CAP_INDEX]) : weeklyCapFare;
				
				}
				
				FromZoneToZone fromZoneToZone = new FromZoneToZone(fromZone, toZone);
				ZoneTravelMaster zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().
															dailyCapFare(dailyCapFare).
																fromZone(fromZoneToZone).
																	offPeakHourFare(offPeakHourFare).
																		peakHourFare(peakHourFare).weeklyCapFare(weeklyCapFare).build();
				
				ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone, zoneTravelMaster);
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
