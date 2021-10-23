package com.sahaj.assignment.tigerCard.visitables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class DayJourneyNode implements IJourneyNode{
	
	private String day;
	private String explanation;
	
	private List<SingleJourneyNode>  singleJourneys = new ArrayList<SingleJourneyNode>();

	
	public DayJourneyNode(DayJourneyNodeBuilder dayJourneyNodeBuilder)
	{
		this.day = dayJourneyNodeBuilder.day;
		this.explanation = dayJourneyNodeBuilder.explanation;
	}
	
	
	public void addSingleJourneyNode(SingleJourneyNode singleJourneyNode)
	{
		this.singleJourneys.add(singleJourneyNode);
	}
	
	public List<SingleJourneyNode> getSingleJourneys()
	{
		return Collections.unmodifiableList(singleJourneys);
	}
	
	public ZoneTravelMaster getFarthestTravelZone()
	{

		ZoneTravelMaster farthestTravelZone = null;
		
		ZoneTravelMaster travelMaster;
		
		for( SingleJourneyNode singleJourneyNode : this.getSingleJourneys() )
		{
			
			travelMaster = ZoneTravelMasterManager.INSTANCE.getZoneMasterDataForFromZoneToZone(singleJourneyNode.getFromZoneToZone());
			
			if( farthestTravelZone == null || farthestTravelZone.getDailyCapFare() < travelMaster.getDailyCapFare() )
			{
				farthestTravelZone = travelMaster;
			}
			
		}
		
		return farthestTravelZone;
		
	}
	
	public static class DayJourneyNodeBuilder 
	{
		private String day;
		private String explanation;
		
		
		public DayJourneyNodeBuilder day(String day) 
		{
			this.day = day;
			return this;
		}
		
		public DayJourneyNodeBuilder explanation(String explanation) 
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
