package com.sahaj.assignment.tigerCard.visitables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class OverallJourneyNode extends JourneyNode
{
	
	private List<WeekJourneyNode> weekJourneys = new ArrayList<WeekJourneyNode>();
	
	
	public void addWeekJourneyNode(WeekJourneyNode weekJourneyNode)
	{
		this.weekJourneys.add(weekJourneyNode);
	}
	
	public List<WeekJourneyNode> getWeekJourneys()
	{
		return Collections.unmodifiableList(weekJourneys);
	}


	public double accept(IVisitor visitor) {
		
		return calculatedFare = visitor.visit(this);
		
	}


	
	
	@Override
	public String toString() {
		
		StringBuilder weekJourneyNodeBuilder = new StringBuilder();
		weekJourneyNodeBuilder.append(this.calculatedFare);
		
		
		return weekJourneyNodeBuilder.toString();
	}
}
