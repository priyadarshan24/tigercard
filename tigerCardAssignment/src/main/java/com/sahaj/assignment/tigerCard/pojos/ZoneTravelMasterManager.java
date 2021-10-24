package com.sahaj.assignment.tigerCard.pojos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ZoneTravelMasterManager 
{
	
	INSTANCE;
	
	//fromZone,toZone,peakHourFare,offPeakHourFare,dailyCap,weeklyCap
	public static final int ZONE_MASTER_FROM_ZONE_INDEX = 0;
	public static final int ZONE_MASTER_TO_ZONE_INDEX = 1;
	public static final int ZONE_MASTER_PEAK_HOUR_FARE_INDEX = 2;
	public static final int ZONE_MASTER_OFF_PEAK_HOUR_INDEX = 3;
	public static final int ZONE_MASTER_DAILY_CAP_INDEX = 4;
	public static final int ZONE_MASTER_WEEKLY_CAP_INDEX = 5;
	
	private Map<FromZoneToZone, ZoneTravelMaster> zoneTravelMasterData = new HashMap<FromZoneToZone, ZoneTravelMaster>();
	
	
	
	public void addZoneTravelMaster(FromZoneToZone key, ZoneTravelMaster value)
	{
		if(key == null || value == null)
		{
			throw new IllegalArgumentException("Zone Key and Zone Data Value cannot be null");
		}
		
		zoneTravelMasterData.put(key, value);
	}
	
	
	public ZoneTravelMaster getZoneMasterDataForFromZoneToZone(FromZoneToZone key)
	{
		if(key == null)
		{
			throw new IllegalArgumentException("Zone Key cannot be null");
		}
		
		return zoneTravelMasterData.get(key);
	}
	
	
	public Map<FromZoneToZone, ZoneTravelMaster> getZoneTravelMasterData()
	{
		return Collections.unmodifiableMap(zoneTravelMasterData);
	}
	
	
	public void initializeZoneFareMasterData(String inputFileFolder, String fileName)
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
				
				validateInput(zoneFareMasterData);
				
				
				createZoneTravelMaster(zoneFareMasterData);
				
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}


	private void validateInput(String[] zoneFareMasterData) {
		if( zoneFareMasterData.length < 5)
		{
			throw new IllegalArgumentException("Wrong Input format type for Zone Fare Master Data");
		}
	}
	
	private void createZoneTravelMaster(String[] zoneFareMasterData)
	{
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

}
