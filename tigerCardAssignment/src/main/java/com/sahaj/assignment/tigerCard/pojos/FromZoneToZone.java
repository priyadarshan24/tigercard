package com.sahaj.assignment.tigerCard.pojos;

public class FromZoneToZone {

	private String fromZone;
	private String toZone;

	public FromZoneToZone(String fromZone, String toZone) 
	{ 
		
		this.fromZone = fromZone;
		this.toZone = toZone;
		
	}

	@Override 
	public boolean equals(Object sourceObjToCompare) 
	{
	  
	  boolean isEqual = false;
	  
	  if( sourceObjToCompare instanceof FromZoneToZone) 
	  { 
		  FromZoneToZone sourceFromZoneToZoneObj =(FromZoneToZone)sourceObjToCompare;
	  
		  String sourceFromZoneToZoneString = sourceFromZoneToZoneObj.getFromZone() + sourceFromZoneToZoneObj.getToZone();
		  String sourceToZoneFromZoneString = sourceFromZoneToZoneObj.getToZone()+ sourceFromZoneToZoneObj.getFromZone();
		  
		  String thisFromZoneToZoneString = this.fromZone + this.toZone;
		  
		  isEqual = 
				  sourceFromZoneToZoneString.equals(thisFromZoneToZoneString) || 
				  		sourceToZoneFromZoneString.equals(thisFromZoneToZoneString) ? true : false;
		  

	  }
		  
	  return isEqual;
	  
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.toString().length();
	}
	

	@Override
	public String toString() {
		StringBuilder fromZoneToZoneBuilder = new StringBuilder();
		fromZoneToZoneBuilder.append(fromZone);
		fromZoneToZoneBuilder.append("-");
		fromZoneToZoneBuilder.append(toZone);
		return fromZoneToZoneBuilder.toString();
	}

	public String getFromZone() {
		return fromZone;
	}

	public String getToZone() {
		return toZone;
	}

}
