package com.sahaj.assignment.tigerCard.pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public enum PeakHourTimingsMasterManager {
	
	INSTANCE;
	
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
	

}
