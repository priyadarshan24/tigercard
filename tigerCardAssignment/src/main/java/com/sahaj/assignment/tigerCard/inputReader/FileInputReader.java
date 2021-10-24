package com.sahaj.assignment.tigerCard.inputReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.TravelTime;
import com.sahaj.assignment.tigerCard.visitables.DayJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.JourneyNode;
import com.sahaj.assignment.tigerCard.visitables.OverallJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.SingleJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNode;

public enum FileInputReader implements IInputReader {
	
	INSTANCE;
	
	//#Inputformat >> day,time,fromZone,toZone
		public static final int INPUT_JOURNEY_DAY_INDEX = 0;
		public static final int INPUT_JOURNEY_TIME_INDEX = 1;
		public static final int INPUT_JOURNEY_FROMZONE_INDEX = 2;
		public static final int INPUT_JOURNEY_TOZONE_INDEX = 3;

	@Override
	public JourneyNode readInput(String inputFileFolder, String fileName) 
	{
		

		
		File inputJourneyFile = new File(inputFileFolder, fileName);
		int lineCount = 0;
		String currentLineRead = null;
		
		Map<String, DayJourneyNode> dayJourneyNodeMap = new HashMap<String, DayJourneyNode>();
		Map<Integer,WeekJourneyNode> weekJourneyNodeMap = new HashMap<Integer, WeekJourneyNode>();
		int weekCounter = 0;
		String lastJourneyDay = null;
		
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
				
				validateInput(inputJourneyData);
				
				SingleJourneyNode singleJourneyNode = createSingleJourneyNode(inputJourneyData);
				String journeyDay = singleJourneyNode.getJourneyDay();
				
				if(isNewWeek(lastJourneyDay, journeyDay) )
				{
					weekCounter++;
				}
				
				String dayJourneyNodeMapKey = journeyDay + weekCounter;
				DayJourneyNode dayJourneyNode =null;
				if( (dayJourneyNode = dayJourneyNodeMap.get(dayJourneyNodeMapKey)) == null)
				{
					dayJourneyNode = new DayJourneyNode(journeyDay);
					dayJourneyNodeMap.put(dayJourneyNodeMapKey, dayJourneyNode);
				}
				
				dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
				
				
				
				WeekJourneyNode weekJourneyNode = null;
				if((weekJourneyNode = weekJourneyNodeMap.get(weekCounter)) == null)
				{
					weekJourneyNode= new WeekJourneyNode();
					weekJourneyNodeMap.put(weekCounter, weekJourneyNode);
					overallJourneyNode.addWeekJourneyNode(weekJourneyNode);
					
				}
				
				if( !weekJourneyNode.contains(dayJourneyNode))
				{
					weekJourneyNode.addDayJourneyNode(dayJourneyNode);
				}
				
				lastJourneyDay = journeyDay;
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return overallJourneyNode;
	
		
	}
	
	private boolean isNewWeek(String lastJourneyDay, String journeyDay) {
		return lastJourneyDay != null && !lastJourneyDay.equals("Monday") && journeyDay.equals("Monday");
	}


	private void validateInput(String[] inputJourneyData) {
		if( inputJourneyData.length < 4)
		{
			throw new IllegalArgumentException("Wrong Input format type for Input Journey");
		}
	}
	
	private SingleJourneyNode createSingleJourneyNode(String[] inputJourneyData)
	{
		String fromZone= null, toZone= null, journeyDay= null, journeyTime = null;
		
		
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
		
		return singleJourneyNode;
	}

}
