package com.sahaj.assignment.tigerCard.visitors;

import com.sahaj.assignment.tigerCard.visitables.DayJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.OverallJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNode;

public interface IVisitor {
	
	
	public void visit(DayJourneyNode dayJourneyNode);
	
	public void visit(WeekJourneyNode weekJourneyNode);
	
	public void visit(OverallJourneyNode overallJourneyNode);

}
