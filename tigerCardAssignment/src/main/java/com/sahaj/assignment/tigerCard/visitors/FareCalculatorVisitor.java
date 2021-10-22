package com.sahaj.assignment.tigerCard.visitors;

import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitables.DayJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.OverallJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.SingleJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNode;

public class FareCalculatorVisitor implements IVisitor{
	
	private ZoneTravelMasterManager zoneTravelMasterManager = ZoneTravelMasterManager.INSTANCE;

	public double visit(DayJourneyNode dayJourneyNode) {
		
		double dayJourneyFare = 0;
		double dailyFareCapForFarthestJourney = 0;
		
		for( SingleJourneyNode singleJourneyNode : dayJourneyNode.getSingleJourneys() )
		{
			
			dayJourneyFare += singleJourneyNode.getJourneyFare();
			
		}
		
		dailyFareCapForFarthestJourney = dayJourneyNode.getFarthestJourneyDailyFareCap();
		
		dayJourneyFare = dayJourneyFare > dailyFareCapForFarthestJourney ? dailyFareCapForFarthestJourney : dayJourneyFare;
		
		return dayJourneyFare;
	}

	public double visit(WeekJourneyNode weekJourneyNode) {
		
		double weeklyJourneyFare = 0;
		
		for( DayJourneyNode dayJourneyNode : weekJourneyNode.getDayJourneys() )
		{
			
			weeklyJourneyFare += dayJourneyNode.accept(this);
			
			//travelMaster = zoneTravelMasterManager.getZoneMasterDataForFromZoneToZone(singleJourneyNode.getFromZoneToZone());
			
			//dailyCapForFarthestFare = dailyCapForFarthestFare < travelMaster.getDailyCapFare() ? travelMaster.getDailyCapFare() : dayJourneyFare;
			
		}
		
		return 0;
		
	}

	public double visit(OverallJourneyNode overallJourneyNode) {
		// TODO Auto-generated method stub
		return 0;
	}

}
