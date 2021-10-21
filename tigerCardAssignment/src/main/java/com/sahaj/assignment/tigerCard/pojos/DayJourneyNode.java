package com.sahaj.assignment.tigerCard.pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DayJourneyNode implements IJourneyNode{
	
	private FromZoneToZone fromZoneToZone;
	private String day;
	private String explanation;
	
	private List<SingleJourneyNode>  singleJourneys = new ArrayList<SingleJourneyNode>();
	
	
	public DayJourneyNode(DayJourneyNodeBuilder dayJourneyNodeBuilder)
	{
		this.fromZoneToZone = dayJourneyNodeBuilder.fromZoneToZone;
		this.day = dayJourneyNodeBuilder.day;
		this.explanation = dayJourneyNodeBuilder.explanation;
	}
	
	
	public double getJourneyFare()
	{
		return 0;
	}
	
	
	public void addSingleJourneyNode(SingleJourneyNode singleJourneyNode)
	{
		this.singleJourneys.add(singleJourneyNode);
	}
	
	public List<SingleJourneyNode> getSingleJourneys()
	{
		return Collections.unmodifiableList(singleJourneys);
	}
	
	public static class DayJourneyNodeBuilder 
	{
		private FromZoneToZone fromZoneToZone;
		private String day;
		private String explanation;
		
		
		public DayJourneyNodeBuilder fromZoneToZone(FromZoneToZone fromZoneToZone) 
		{
			this.fromZoneToZone = fromZoneToZone;
			return this;
		}
		
		
		public DayJourneyNodeBuilder day(String day) 
		{
			this.day = day;
			return this;
		}
		
		public DayJourneyNodeBuilder fromZoneToZone(String explanation) 
		{
			this.explanation = explanation;
			return this;
		}
		
		
		public DayJourneyNode build()
		{
			return new DayJourneyNode(this);
		}
		
		
		
	}
	

}
