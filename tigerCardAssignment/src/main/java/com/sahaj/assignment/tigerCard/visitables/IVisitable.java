package com.sahaj.assignment.tigerCard.visitables;

import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public interface IVisitable {
	
	public double accept(IVisitor visitor);

}
