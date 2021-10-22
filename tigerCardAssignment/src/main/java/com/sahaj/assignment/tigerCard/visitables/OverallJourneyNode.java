package com.sahaj.assignment.tigerCard.visitables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public class OverallJourneyNode implements IJourneyNode
{
	
	private List<WeekJourneyNode> weekJourneys = new ArrayList<WeekJourneyNode>();
	
	
	public double getJourneyFare()
	{
		return 0;
	}
	
	
	public void addWeekJourneyNode(WeekJourneyNode weekJourneyNode)
	{
		this.weekJourneys.add(weekJourneyNode);
	}
	
	public List<WeekJourneyNode> getWeekJourneys()
	{
		return Collections.unmodifiableList(weekJourneys);
	}


	public double accept(IVisitor visitor) {
		
		return visitor.visit(this);
	}

}
