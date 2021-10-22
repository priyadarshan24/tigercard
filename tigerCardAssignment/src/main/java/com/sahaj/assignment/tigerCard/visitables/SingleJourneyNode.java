package com.sahaj.assignment.tigerCard.visitables;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.TravelTime;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class SingleJourneyNode implements IJourneyNode{
	
	public FromZoneToZone getFromZoneToZone() {
		return fromZoneToZone;
	}



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
		double journeyFare = 0;
		
		ZoneTravelMasterManager travelMasterManager = ZoneTravelMasterManager.INSTANCE;
		ZoneTravelMaster travelMaster = travelMasterManager.getZoneMasterDataForFromZoneToZone(fromZoneToZone);
		
		if( this.travelTime.isPeakHourTravelTime() )
		{
			journeyFare = travelMaster.getPeakHourFare();
			setExplanation("Peak Hour Fare Applied");
		}
		else
		{
			journeyFare = travelMaster.getOffPeakHourFare();
			setExplanation("Off Peak Hour Fare Applied");
		}
		
		
		return journeyFare;
		
	}
	
	
	public String getExplanation() {
		return explanation;
	}


	private void setExplanation(String explanation) {
		this.explanation = explanation;
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



	public double accept(IVisitor visitor) {
		// TODO Auto-generated method stub
		
		return 0;
		
	}
	

}
