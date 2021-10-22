package com.sahaj.assignment.tigerCard.visitables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

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
	
	public double getFarthestJourneyDailyFareCap()
	{

		double dailyDareCapForFarthestJourney = 0;
		ZoneTravelMaster travelMaster;
		
		for( SingleJourneyNode singleJourneyNode : this.getSingleJourneys() )
		{
			
			travelMaster = ZoneTravelMasterManager.INSTANCE.getZoneMasterDataForFromZoneToZone(singleJourneyNode.getFromZoneToZone());
			
			dailyDareCapForFarthestJourney = dailyDareCapForFarthestJourney < travelMaster.getDailyCapFare() ? travelMaster.getDailyCapFare() : dailyDareCapForFarthestJourney;
			
		}
		
		return dailyDareCapForFarthestJourney;
		
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

	public double accept(IVisitor visitor) {
		
		return 0;
		
	}
	

}
