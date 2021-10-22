package com.sahaj.assignment.tigerCard.visitors;

import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.visitables.DayJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.OverallJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.SingleJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNode;

public class FareCalculatorVisitor implements IVisitor{

	public double visit(DayJourneyNode dayJourneyNode) {
		
		double dayJourneyFare = 0;
		ZoneTravelMaster farthestTravelZone = null;
		
		for( SingleJourneyNode singleJourneyNode : dayJourneyNode.getSingleJourneys() )
		{
			
			dayJourneyFare += singleJourneyNode.getJourneyFare();
			
		}
		
		farthestTravelZone = dayJourneyNode.getFarthestTravelZone();
		
		dayJourneyFare = dayJourneyFare > farthestTravelZone.getDailyCapFare() ? farthestTravelZone.getDailyCapFare() : dayJourneyFare;
		
		return dayJourneyFare;
	}

	public double visit(WeekJourneyNode weekJourneyNode) {
		
		double weeklyJourneyFare = 0;
		
		for( DayJourneyNode dayJourneyNode : weekJourneyNode.getDayJourneys() )
		{
			
			weeklyJourneyFare += dayJourneyNode.accept(this);
			
		}
		
		double weeklyCappedFareForFarthestZoneTravelled = weekJourneyNode.getFarthestTravelZoneDuringWeek().getWeeklyCapFare();
		
		weeklyJourneyFare = weeklyJourneyFare > weeklyCappedFareForFarthestZoneTravelled ? weeklyCappedFareForFarthestZoneTravelled : weeklyJourneyFare;
		
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

}
