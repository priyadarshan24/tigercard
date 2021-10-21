package com.sahaj.assignment.tigerCard.pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OverallJourneyNode implements IJourneyNode
{
	
	private List<WeekJourneyNode> weekJourneys = new ArrayList<WeekJourneyNode>();
	
	
	public double getJourneyFare()
	{
		return 0;
	}
	
	
	public void addSingleJourneyNode(WeekJourneyNode weekJourneyNode)
	{
		this.weekJourneys.add(weekJourneyNode);
	}
	
	public List<WeekJourneyNode> getSingleJourneys()
	{
		return Collections.unmodifiableList(weekJourneys);
	}

}
