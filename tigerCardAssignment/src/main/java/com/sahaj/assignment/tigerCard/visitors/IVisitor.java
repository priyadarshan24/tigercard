package com.sahaj.assignment.tigerCard.visitors;

import com.sahaj.assignment.tigerCard.visitables.DayJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.OverallJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.SingleJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNode;

public interface IVisitor {
	
	
	public double visit(DayJourneyNode dayJourneyNode);
	
	public double visit(WeekJourneyNode weekJourneyNode);
	
	public double visit(OverallJourneyNode overallJourneyNode);
	
	public double visit(SingleJourneyNode singJourneyNode);

}
