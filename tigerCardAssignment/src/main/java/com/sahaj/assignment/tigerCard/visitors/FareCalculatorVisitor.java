package com.sahaj.assignment.tigerCard.visitors;

import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitables.DayJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.OverallJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.SingleJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNode;

public class FareCalculatorVisitor implements IVisitor{

	public double visit(DayJourneyNode dayJourneyNode) {
		
		double dayJourneyFare = 0;
	
		ZoneTravelMaster farthestTravelZone = dayJourneyNode.getFarthestTravelZone();
		
		for( SingleJourneyNode singleJourneyNode : dayJourneyNode.getSingleJourneys() )
		{
			
			dayJourneyFare += singleJourneyNode.accept(this);
			
		}
		
		if( dayJourneyFare > farthestTravelZone.getDailyCapFare() )
		{
			dayJourneyFare = farthestTravelZone.getDailyCapFare();
			dayJourneyNode.setExplanation("Daily cap reached");
		}
		else
		{
			dayJourneyNode.setExplanation("Daily cap not reached");
		}
		
		
		return dayJourneyFare;
	}

	public double visit(WeekJourneyNode weekJourneyNode) {
		
		double weeklyJourneyFare = 0;
		
		ZoneTravelMaster farthestTravelZone = weekJourneyNode.getFarthestTravelZoneDuringWeek();
		
		for( DayJourneyNode dayJourneyNode : weekJourneyNode.getDayJourneys() )
		{
			
			weeklyJourneyFare += dayJourneyNode.accept(this);
			
		}
		
		
		if( weeklyJourneyFare > farthestTravelZone.getWeeklyCapFare() )
		{
			weeklyJourneyFare = farthestTravelZone.getWeeklyCapFare();
			weekJourneyNode.setExplanation("Weekly cap reached");
		}
		else
		{
			weekJourneyNode.setExplanation("Weekly cap not reached");
		}
		
		return weeklyJourneyFare;
		
	}

	public double visit(OverallJourneyNode overallJourneyNode) {
		
		double totalJourneyFare = 0;
		
		for( WeekJourneyNode weekJourneyNode : overallJourneyNode.getWeekJourneys() )
		{
			totalJourneyFare += weekJourneyNode.accept(this);
		}
		return totalJourneyFare;
	}
	
	
	public double visit(SingleJourneyNode singJourneyNode) {
		
		double journeyFare = 0;
		
		ZoneTravelMasterManager travelMasterManager = ZoneTravelMasterManager.INSTANCE;
		ZoneTravelMaster travelMaster = travelMasterManager.getZoneMasterDataForFromZoneToZone(singJourneyNode.getFromZoneToZone());
		
		if( singJourneyNode.getTravelTime().isPeakHourTravelTime() )
		{
			journeyFare = travelMaster.getPeakHourFare();
			singJourneyNode.setExplanation("Peak Hour Fare Applied");
		}
		else
		{
			journeyFare = travelMaster.getOffPeakHourFare();
			singJourneyNode.setExplanation("Off Peak Hour Fare Applied");
		}
		
		
		return journeyFare;
	}
	
	
	
	

}
