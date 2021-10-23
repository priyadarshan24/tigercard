package com.sahaj.assignment.tigerCard.visitables;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.TravelTime;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class SingleJourneyNode implements IJourneyNode{
	
	public FromZoneToZone getFromZoneToZone() {
		return fromZoneToZone;
	}



	private FromZoneToZone fromZoneToZone;
	private TravelTime travelTime;
	private double calculatedFare;
	
	
	public TravelTime getTravelTime() {
		return travelTime;
	}
	
	private String explanation;
	
	
	private SingleJourneyNode(SingleJourneyNodeBuilder singleJourneyNodeBuilder)
	{
		this.fromZoneToZone = singleJourneyNodeBuilder.fromZoneToZone;
		this.travelTime = singleJourneyNodeBuilder.travelTime;
		this.explanation = singleJourneyNodeBuilder.explanation;
	}
	
	
	/*
	 * public double getJourneyFare() { double journeyFare = 0;
	 * 
	 * ZoneTravelMasterManager travelMasterManager =
	 * ZoneTravelMasterManager.INSTANCE; ZoneTravelMaster travelMaster =
	 * travelMasterManager.getZoneMasterDataForFromZoneToZone(fromZoneToZone);
	 * 
	 * if( this.travelTime.isPeakHourTravelTime() ) { journeyFare =
	 * travelMaster.getPeakHourFare(); setExplanation("Peak Hour Fare Applied"); }
	 * else { journeyFare = travelMaster.getOffPeakHourFare();
	 * setExplanation("Off Peak Hour Fare Applied"); }
	 * 
	 * 
	 * return journeyFare;
	 * 
	 * }
	 */
	
	
	public String getExplanation() {
		return explanation;
	}


	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}


	public static class SingleJourneyNodeBuilder 
	{
		private FromZoneToZone fromZoneToZone;
		private TravelTime travelTime;
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
		
		calculatedFare = visitor.visit(this);
		System.out.println("Single Journey Calculation:" + this.toString());
		return calculatedFare;
		
	}


	@Override
	public String toString() {
		StringBuilder singleJourneyNodeBuilder = new StringBuilder();
		singleJourneyNodeBuilder.append(this.travelTime);
		singleJourneyNodeBuilder.append("|");
		singleJourneyNodeBuilder.append(this.fromZoneToZone);
		singleJourneyNodeBuilder.append("|");
		singleJourneyNodeBuilder.append(this.calculatedFare);
		singleJourneyNodeBuilder.append("|");
		singleJourneyNodeBuilder.append(this.explanation);
		
		
		return singleJourneyNodeBuilder.toString();
	}
	
	
	

}
