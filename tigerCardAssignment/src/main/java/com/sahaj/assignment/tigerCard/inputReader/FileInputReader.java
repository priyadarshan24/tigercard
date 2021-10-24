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
		
	private OverallJourneyNode overallJourneyNode = new OverallJourneyNode();
	private Map<String, DayJourneyNode> dayJourneyNodeMap = new HashMap<String, DayJourneyNode>();
	private Map<Integer,WeekJourneyNode> weekJourneyNodeMap = new HashMap<Integer, WeekJourneyNode>();
		
	@Override
	public JourneyNode readInput(String inputFileFolder, String fileName) 
	{
		
		File inputJourneyFile = new File(inputFileFolder, fileName);
		int lineCount = 0;
		String currentLineRead = null;
		
		
		int weekCounter = 0;
		String lastJourneyDay = null;
		
		
		
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
				
				if(isNewWeek(lastJourneyDay, singleJourneyNode.getJourneyDay()) )
				{
					weekCounter++;
				}
				
				DayJourneyNode dayJourneyNode =createDayJourneyNode(singleJourneyNode, weekCounter);
				
				createWeekJourneyNode(dayJourneyNode, weekCounter);
				
				lastJourneyDay = singleJourneyNode.getJourneyDay();
				
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return overallJourneyNode;
	
		
	}
	
	private DayJourneyNode createDayJourneyNode(SingleJourneyNode singleJourneyNode, int weekCounter)
	{
		String dayJourneyNodeMapKey = singleJourneyNode.getJourneyDay() + weekCounter;
		DayJourneyNode dayJourneyNode =null;
		if( (dayJourneyNode = dayJourneyNodeMap.get(dayJourneyNodeMapKey)) == null)
		{
			dayJourneyNode = new DayJourneyNode(singleJourneyNode.getJourneyDay());
			dayJourneyNodeMap.put(dayJourneyNodeMapKey, dayJourneyNode);
		}
		
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		return dayJourneyNode;
	}
	
	private WeekJourneyNode createWeekJourneyNode(DayJourneyNode dayJourneyNode, int weekCounter )
	{
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
		
		return weekJourneyNode;
		
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
	
	
	private boolean isNewWeek(String lastJourneyDay, String journeyDay) {
		return lastJourneyDay != null && !lastJourneyDay.equals("Monday") && journeyDay.equals("Monday");
	}


	private void validateInput(String[] inputJourneyData) {
		if( inputJourneyData.length < 4)
		{
			throw new IllegalArgumentException("Wrong Input format type for Input Journey");
		}
	}

}
