package com.sahaj.assignment.tigerCard.visitables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class WeekJourneyNode extends JourneyNode
{
	
	
	private List<DayJourneyNode> dayJourneys = new ArrayList<DayJourneyNode>();
	
	
	public void addDayJourneyNode(DayJourneyNode dayJourneyNode)
	{
		this.dayJourneys.add(dayJourneyNode);
	}
	
	public List<DayJourneyNode> getDayJourneys()
	{
		return Collections.unmodifiableList(dayJourneys);
	}
	
	public boolean contains(DayJourneyNode dayJourneyNode)
	{
		return dayJourneys.contains(dayJourneyNode);
	}
	
	
	public ZoneTravelMaster getFarthestTravelZoneDuringWeek()
	{
		
		ZoneTravelMaster farthestTravelZoneDuringWeek = null;
		
		for( DayJourneyNode dayJourneyNode : this.getDayJourneys() )
		{
			ZoneTravelMaster iteratedDayFarthestTravelZone = dayJourneyNode.getFarthestTravelZone();
			
			if( farthestTravelZoneDuringWeek == null ||
					iteratedDayFarthestTravelZone.getDailyCapFare() > farthestTravelZoneDuringWeek.getDailyCapFare() )
			{
				farthestTravelZoneDuringWeek = iteratedDayFarthestTravelZone;
			}
			
			
		}
		
		return farthestTravelZoneDuringWeek;
		
	}
	
	

	public double accept(IVisitor visitor) {
		
		return calculatedFare = visitor.visit(this);
		
	}
	
	
	@Override
	public String toString() {
		StringBuilder weekJourneyNodeBuilder = new StringBuilder();
		weekJourneyNodeBuilder.append(this.calculatedFare);
		weekJourneyNodeBuilder.append("|");
		weekJourneyNodeBuilder.append(this.explanation);
		
		
		return weekJourneyNodeBuilder.toString();
	}

}
