package com.sahaj.assignment.tigerCard.visitables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class DayJourneyNode extends JourneyNode{
	
	
	private String day;
	
	private List<SingleJourneyNode>  singleJourneys = new ArrayList<SingleJourneyNode>();

	
	public DayJourneyNode(String day)
	{
		this.day = day;
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
	

	public double accept(IVisitor visitor) {
		
		calculatedFare = visitor.visit(this);
		System.out.println("Day Journey Calculation:" + this.toString());
		return calculatedFare;
		
	}
	
	
	@Override
	public String toString() {
		StringBuilder singleJourneyNodeBuilder = new StringBuilder();
		singleJourneyNodeBuilder.append(day);
		singleJourneyNodeBuilder.append("|");
		singleJourneyNodeBuilder.append(this.calculatedFare);
		singleJourneyNodeBuilder.append("|");
		singleJourneyNodeBuilder.append(this.explanation);
		
		
		return singleJourneyNodeBuilder.toString();
	}

}
