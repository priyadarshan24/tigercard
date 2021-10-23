package com.sahaj.assignment.tigerCard.pojos;

import java.time.DayOfWeek;

/**
 * Performs Travel Time related operations on Travel Time Data
 * @author pd
 *
 */
public class TravelTime {
	
	public static final int WEEKDAY_MORNING_PEAKHOUR_START_TIME_IN_SECS = 0;
	public static final int WEEKDAY_MORNING_PEAKHOUR_END_TIME_IN_SECS = 0;
	
	public static final int WEEKDAY_EVENING_PEAKHOUR_START_TIME_IN_SECS = 0;
	public static final int WEEKDAY_EVENING_PEAKHOUR_END_TIME_IN_SECS = 0;
	
	
	public static final int WEEKEND_MORNING_PEAKHOUR_START_TIME_IN_SECS = 0;
	public static final int WEEKEND_MORNING_PEAKHOUR_END_TIME_IN_SECS = 0;
	
	public static final int WEEKEND_EVENING_PEAKHOUR_START_TIME_IN_SECS = 0;
	public static final int WEEKEND_EVENING_PEAKHOUR_END_TIME_IN_SECS = 0;
	
	
	
	
	private String time;
	private String day;
	
	
	public boolean isPeakHourTravelTime()
	{
		return isPeakHourWeekend() || isPeakHourWeekday() ? true : false;
	}
	
	
	private int getTimeinSeconds()
	{
		
		int timeInSeconds = 0;
		
		if( time ==null || time.trim().isBlank())
		{
			throw new IllegalArgumentException("JourneyTime cannot be empty");
		}
		
		
			String[] splitTime = time.split(":");
			
			
			
			for(int index = 0; index < splitTime.length; index++)
			{
				
				if( index == 0)
				{
					timeInSeconds += Integer.parseInt(splitTime[0])*3600;
				}
				else
				{
					timeInSeconds += Integer.parseInt(splitTime[1])*60;
				}
			}
		
		
		return timeInSeconds;
		
		
	}
	
	private boolean isPeakHourWeekend()
	{
		int timeInSeconds = getTimeinSeconds();
		return isWeekend() && isPeakTimingForWeekend(timeInSeconds) ? true : false;
	}
	
	private boolean isPeakHourWeekday()
	{
		int timeInSeconds = getTimeinSeconds();
		return !isWeekend() && isPeakTimingForWeekday(timeInSeconds) ? true : false;
	}


	private boolean isPeakTimingForWeekend(int timeInSeconds) {
		return liesBetween(timeInSeconds, WEEKEND_MORNING_PEAKHOUR_START_TIME_IN_SECS, WEEKEND_MORNING_PEAKHOUR_END_TIME_IN_SECS)
				|| liesBetween(timeInSeconds, WEEKEND_EVENING_PEAKHOUR_START_TIME_IN_SECS, WEEKEND_EVENING_PEAKHOUR_END_TIME_IN_SECS);
	}
	
	private boolean isPeakTimingForWeekday(int timeInSeconds) {
		return liesBetween(timeInSeconds, WEEKDAY_MORNING_PEAKHOUR_START_TIME_IN_SECS, WEEKDAY_MORNING_PEAKHOUR_END_TIME_IN_SECS)
				|| liesBetween(timeInSeconds, WEEKDAY_EVENING_PEAKHOUR_START_TIME_IN_SECS, WEEKDAY_EVENING_PEAKHOUR_END_TIME_IN_SECS);
	}
	
	private boolean isWeekend()
	{
		boolean isWeekend = DayOfWeek.SATURDAY.name().equals(day) || DayOfWeek.SUNDAY.name().equals(day) ? true : false;
		
		return isWeekend;
	}
	
	private boolean liesBetween(int inputTime, int startTime, int endTime)
	{
		return inputTime >= startTime && inputTime <= endTime ? true : false;
	}
	

}
