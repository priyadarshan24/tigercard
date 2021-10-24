package com.sahaj.assignment.tigerCard.visitables;

public abstract class JourneyNode implements IVisitable {
	
	
	protected double calculatedFare;
	protected String explanation;
	
	public double getCalculatedFare() {
		return calculatedFare;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	
	

}
