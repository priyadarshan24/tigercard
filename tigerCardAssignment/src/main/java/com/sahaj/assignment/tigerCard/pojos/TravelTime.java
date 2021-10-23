package com.sahaj.assignment.tigerCard.pojos;

import java.time.DayOfWeek;

import com.sahaj.assignment.tigerCard.utils.TigerCardUtil;

/**
 * Performs Travel Time related operations on Travel Time Data
 * @author pd
 *
 */
public class TravelTime {
	
	private String time;
	private String day;
	
	
	public boolean isPeakHourTravelTime()
	{
		return isPeakHourWeekend() || isPeakHourWeekday() ? true : false;
	}
	
	
	
	private boolean isPeakHourWeekend()
	{
		long timeInSeconds = TigerCardUtil.INSTANCE.getTimeinSeconds(time);
		return isWeekend() && isPeakTimingForWeekend(timeInSeconds) ? true : false;
	}
	
	private boolean isPeakHourWeekday()
	{
		long timeInSeconds = TigerCardUtil.INSTANCE.getTimeinSeconds(time);
		return !isWeekend() && isPeakTimingForWeekday(timeInSeconds) ? true : false;
	}


	private boolean isPeakTimingForWeekend(long timeInSeconds) {
		
		boolean isPeakTimingForWeeknd = false;
		for(PeakHourTimings peakHourTimings : PeakHourTimingsMasterManager.INSTANCE.getWeekendPeakHourTimings())
		{
			if ( liesBetween(timeInSeconds, peakHourTimings.getPeakHourStartTimeInSeconds(), peakHourTimings.getPeakHourEndTimeInSeconds()) )
			{
				isPeakTimingForWeeknd = true;
				break;
			}
										
		}
		
		return isPeakTimingForWeeknd;
	}
	
	private boolean isPeakTimingForWeekday(long timeInSeconds) {
		boolean isPeakTimingForWeekDay = false;
		
		for(PeakHourTimings peakHourTimings : PeakHourTimingsMasterManager.INSTANCE.getWeekDayPeakHourTimings())
		{
			if ( liesBetween(timeInSeconds, peakHourTimings.getPeakHourStartTimeInSeconds(), peakHourTimings.getPeakHourEndTimeInSeconds()) )
			{
				isPeakTimingForWeekDay = true;
				break;
			}
										
		}
		return isPeakTimingForWeekDay;
	}
	
	private boolean isWeekend()
	{
		boolean isWeekend = DayOfWeek.SATURDAY.name().equals(day) || DayOfWeek.SUNDAY.name().equals(day) ? true : false;
		
		return isWeekend;
	}
	
	private boolean liesBetween(long inputTime, long startTime, long endTime)
	{
		return inputTime >= startTime && inputTime <= endTime ? true : false;
	}
	

}
