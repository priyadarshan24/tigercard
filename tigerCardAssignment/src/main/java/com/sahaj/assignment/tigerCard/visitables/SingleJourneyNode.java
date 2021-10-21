package com.sahaj.assignment.tigerCard.visitables;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.TravelTime;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class SingleJourneyNode implements IJourneyNode{
	
	private FromZoneToZone fromZoneToZone;
	private TravelTime travelTime;
	private String day;
	private String explanation;
	
	
	public SingleJourneyNode(SingleJourneyNodeBuilder singleJourneyNodeBuilder)
	{
		this.fromZoneToZone = singleJourneyNodeBuilder.fromZoneToZone;
		this.travelTime = singleJourneyNodeBuilder.travelTime;
		this.day = singleJourneyNodeBuilder.day;
		this.explanation = singleJourneyNodeBuilder.explanation;
	}
	
	
	public double getJourneyFare()
	{
		return 0;
	}
	
	
	
	public static class SingleJourneyNodeBuilder 
	{
		private FromZoneToZone fromZoneToZone;
		private TravelTime travelTime;
		private String day;
		private String explanation;
		
		
		public SingleJourneyNodeBuilder fromZoneToZone(FromZoneToZone fromZoneToZone) 
		{
			this.fromZoneToZone = fromZoneToZone;
			return this;
		}
		
		public SingleJourneyNodeBuilder travelTime(TravelTime travelTime) 
		{
			this.travelTime = travelTime;
			return this;
		}
		
		public SingleJourneyNodeBuilder day(String day) 
		{
			this.day = day;
			return this;
		}
		
		public SingleJourneyNodeBuilder fromZoneToZone(String explanation) 
		{
			this.explanation = explanation;
			return this;
		}
		
		
		public SingleJourneyNode build()
		{
			return new SingleJourneyNode(this);
		}
		
		
		
	}



	public void accept(IVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
	

}
