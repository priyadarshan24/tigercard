package com.sahaj.assignment.tigerCard.visitables;

import com.sahaj.assignment.tigerCard.visitors.IVisitor;

public interface IVisitable {
	
	public void accept(IVisitor visitor);

}
