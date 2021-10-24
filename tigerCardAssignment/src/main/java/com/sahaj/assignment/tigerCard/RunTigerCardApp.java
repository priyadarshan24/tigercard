package com.sahaj.assignment.tigerCard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimings;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimingsMasterManager;
import com.sahaj.assignment.tigerCard.pojos.TravelTime;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitables.DayJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.IJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.OverallJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.SingleJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNode;

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
	
	
	//#Inputformat >> day,time,fromZone,toZone
	public static final int INPUT_JOURNEY_DAY_INDEX = 0;
	public static final int INPUT_JOURNEY_TIME_INDEX = 1;
	public static final int INPUT_JOURNEY_FROMZONE_INDEX = 2;
	public static final int INPUT_JOURNEY_TOZONE_INDEX = 3;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String pathSeperator = System.getProperty("file.separator");
		String inputFileFolder = System.getProperty("user.dir") + pathSeperator + "input" + pathSeperator;
		
		
		System.out.println(inputFileFolder);
		
		RunTigerCardApp runTigerCardApp = new RunTigerCardApp();
		//runTigerCardApp.readPeakHourTimings(inputFileFolder, "peakHourMaster.txt");
		//runTigerCardApp.readZoneFareMasterData(inputFileFolder, "zoneFareMaster.txt");
		
		IJourneyNode iJourneyNode = runTigerCardApp.readInputJourney(inputFileFolder, "inputjourneys.txt");
		
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
	
	
	@SuppressWarnings("unused")
	public IJourneyNode readInputJourney(String inputFileFolder, String fileName)
	{
		
		File inputJourneyFile = new File(inputFileFolder, fileName);
		int lineCount = 0;
		String currentLineRead = null;
		
		Map<String, DayJourneyNode> dayJourneyNodeMap = new HashMap<String, DayJourneyNode>();
		Map<Integer,WeekJourneyNode> weekJourneyNodeMap = new HashMap<Integer, WeekJourneyNode>();
		int weekCounter = 0;
		
		OverallJourneyNode overallJourneyNode = new OverallJourneyNode();
		
		try (BufferedReader bufferedFileReader = new BufferedReader(new FileReader(inputJourneyFile)) ){
			
			
			while((currentLineRead = bufferedFileReader.readLine()) != null)
			{
				if( lineCount == 0)
				{
					lineCount++;
					continue;
				}
				
				String[] inputJourneyData = currentLineRead.split(",");
				
				if( inputJourneyData.length < 4)
				{
					throw new IllegalArgumentException("Wrong Input format type for Input Journey");
				}
				
				String fromZone= null, toZone= null, journeyDay= null, journeyTime = null;
				
				String lastJourneyDay = null;
				
				for(int index = 0; index <inputJourneyData.length; index++)
				{	
					fromZone = index == INPUT_JOURNEY_FROMZONE_INDEX ? inputJourneyData[INPUT_JOURNEY_FROMZONE_INDEX] : fromZone;
					toZone = index == INPUT_JOURNEY_TOZONE_INDEX ? inputJourneyData[INPUT_JOURNEY_TOZONE_INDEX] :toZone;
					journeyDay = index == INPUT_JOURNEY_DAY_INDEX ? inputJourneyData[INPUT_JOURNEY_DAY_INDEX] : journeyDay;
					journeyTime = index == INPUT_JOURNEY_TIME_INDEX ? inputJourneyData[INPUT_JOURNEY_TIME_INDEX] : journeyTime;
				
				}
				
				
				FromZoneToZone fromZoneToZone = new FromZoneToZone(fromZone, toZone);
				TravelTime travelTime = new TravelTime(journeyDay, journeyTime);
				SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().
																fromZoneToZone(fromZoneToZone).travelTime(travelTime).build();
				
				String dayJourneyNodeMapKey = journeyDay + weekCounter;
				DayJourneyNode dayJourneyNode =null;
				if( (dayJourneyNode = dayJourneyNodeMap.get(dayJourneyNodeMapKey)) == null)
				{
					dayJourneyNode = new DayJourneyNode(journeyDay);
					dayJourneyNodeMap.put(dayJourneyNodeMapKey, dayJourneyNode);
				}
				
				dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
				
				if(lastJourneyDay != null && !lastJourneyDay.equals("Monday") && journeyDay.equals("Monday") )
				{
					weekCounter++;
				}
				
				WeekJourneyNode weekJourneyNode = null;
				if((weekJourneyNode = weekJourneyNodeMap.get(weekCounter)) == null)
				{
					weekJourneyNode= new WeekJourneyNode();
					weekJourneyNodeMap.put(weekCounter, weekJourneyNode);
					overallJourneyNode.addWeekJourneyNode(weekJourneyNode);
					
				}
				
				weekJourneyNode.addDayJourneyNode(dayJourneyNode);
				
				lastJourneyDay = journeyDay;
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return overallJourneyNode;
	}
	
	
	

}
