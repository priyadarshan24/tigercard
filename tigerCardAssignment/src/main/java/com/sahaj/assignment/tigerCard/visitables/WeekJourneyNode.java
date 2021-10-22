package com.sahaj.assignment.tigerCard.visitables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
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
	
	
	public void addDayJourneyNode(DayJourneyNode dayJourneyNode)
	{
		this.dayJourneys.add(dayJourneyNode);
	}
	
	public List<DayJourneyNode> getDayJourneys()
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

	public double accept(IVisitor visitor) {
		
		return visitor.visit(this);
		
	}
	

}
