package com.sahaj.assignment.tigerCard.visitables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class WeekJourneyNode implements IJourneyNode
{
	
	private FromZoneToZone fromZoneToZone;
	private String day;
	private String explanation;
	
	private List<DayJourneyNode> dayJourneys = new ArrayList<DayJourneyNode>();
	
	
	public WeekJourneyNode(WeekJourneyNodeBuilder weekJourneyNodeBuilder)
	{
		this.fromZoneToZone = weekJourneyNodeBuilder.fromZoneToZone;
		this.day = weekJourneyNodeBuilder.day;
		this.explanation = weekJourneyNodeBuilder.explanation;
	}
	
	
	public double getJourneyFare()
	{
		return 0;
	}
	
	
	public void addSingleJourneyNode(DayJourneyNode dayJourneyNode)
	{
		this.dayJourneys.add(dayJourneyNode);
	}
	
	public List<DayJourneyNode> getSingleJourneys()
	{
		return Collections.unmodifiableList(dayJourneys);
	}
	
	public static class WeekJourneyNodeBuilder 
	{
		private FromZoneToZone fromZoneToZone;
		private String day;
		private String explanation;
		
		
		public WeekJourneyNodeBuilder fromZoneToZone(FromZoneToZone fromZoneToZone) 
		{
			this.fromZoneToZone = fromZoneToZone;
			return this;
		}
		
		
		public WeekJourneyNodeBuilder day(String day) 
		{
			this.day = day;
			return this;
		}
		
		public WeekJourneyNodeBuilder fromZoneToZone(String explanation) 
		{
			this.explanation = explanation;
			return this;
		}
		
		
		public WeekJourneyNode build()
		{
			return new WeekJourneyNode(this);
		}
		
		
		
	}

	public void accept(IVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
	

}
