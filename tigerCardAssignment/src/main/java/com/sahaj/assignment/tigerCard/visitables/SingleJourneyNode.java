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
		
		
		public SingleJourneyNode build()
		{
			return new SingleJourneyNode(this);
		}
		
		
	}



	public double accept(IVisitor visitor) {
		
		return calculatedFare = visitor.visit(this);

		
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
