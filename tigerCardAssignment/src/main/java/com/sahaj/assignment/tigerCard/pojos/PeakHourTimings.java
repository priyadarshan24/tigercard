package com.sahaj.assignment.tigerCard.pojos;

import com.sahaj.assignment.tigerCard.utils.TigerCardUtil;

public class PeakHourTimings {
	
	private String peakHourStart;
	private String peakHourEnd;
	
	private long peakHourStartTimeInSeconds;
	private long peakHourEndTimeInSeconds;
	
	
	public PeakHourTimings(String peakHourStart, String peakHourEnd)
	{
		this.peakHourStart = peakHourStart;
		this.peakHourEnd = peakHourEnd;
	}


	public long getPeakHourStartTimeInSeconds() 
	{
		
		peakHourStartTimeInSeconds = this.peakHourStartTimeInSeconds == 0 ? TigerCardUtil.INSTANCE.getTimeinSeconds(peakHourStart) : peakHourStartTimeInSeconds;
		
		return peakHourStartTimeInSeconds;
	}


	public long getPeakHourEndTimeInSeconds() {
		
		peakHourEndTimeInSeconds = this.peakHourEndTimeInSeconds == 0 ? TigerCardUtil.INSTANCE.getTimeinSeconds(peakHourEnd) : peakHourEndTimeInSeconds;
		
		return peakHourEndTimeInSeconds;
	}
	
	
	
	

}
