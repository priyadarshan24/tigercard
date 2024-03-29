package com.sahaj.assignment.tigerCard.utils;

public enum TigerCardUtil {
	
	
	INSTANCE;
	
	private static final String SPLIT_TOKEN = ":";
	
	public long getTimeinSeconds(String inputTime)
	{
		
		int timeInSeconds = 0;
		
		validateInputTime(inputTime);
		
		
		String[] splitTime = inputTime.split(SPLIT_TOKEN);
		
		validateSplitTime(splitTime);
		
		
		for(int index = 0; index < splitTime.length; index++)
		{
			
			timeInSeconds += index == 0 ? Integer.parseInt(splitTime[0])*3600 : Integer.parseInt(splitTime[1])*60;
			
		}
		
		
		return timeInSeconds;
		
	}

	private void validateSplitTime(String[] splitTime) 
	{
		if( splitTime == null || splitTime.length <= 0)
		{
			throw new IllegalArgumentException("Input time format is not supported");
		}
	}

	private void validateInputTime(String inputTime) 
	{
		if( inputTime ==null || inputTime.trim().isBlank())
		{
			throw new IllegalArgumentException("Time Parameter cannot be empty");
		}
	}

}
