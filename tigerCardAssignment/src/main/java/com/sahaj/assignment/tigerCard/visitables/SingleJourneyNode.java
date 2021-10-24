package com.sahaj.assignment.tigerCard.visitables;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.TravelTime;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class SingleJourneyNode extends JourneyNode{
	
	


	private FromZoneToZone fromZoneToZone;
	private TravelTime travelTime;
	
	
	public FromZoneToZone getFromZoneToZone() {
		return fromZoneToZone;
	}
	
	public TravelTime getTravelTime() {
		return travelTime;
	}
		
	
	private SingleJourneyNode(SingleJourneyNodeBuilder singleJourneyNodeBuilder)
	{
		this.fromZoneToZone = singleJourneyNodeBuilder.fromZoneToZone;
		this.travelTime = singleJourneyNodeBuilder.travelTime;
		this.explanation = singleJourneyNodeBuilder.explanation;
	}
	
	public String getExplanation() {
		return explanation;
	}

	//Encapsulation of Field
	public void setExplanation(String explanation) {
		if( explanation != null && !explanation.isBlank() )
		{
			this.explanation = explanation;
		}
	}
	
	//hide the delegate pattern
	public String getJourneyDay()
	{
		return this.travelTime.getDay();
	}

	
	//Builder Design pattern to handle telescopic constructor problem
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
		//System.out.println("Single Journey Calculation:" + this.toString());
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
