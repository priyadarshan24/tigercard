package com.sahaj.assignment.tigerCard.pojos;

public class FromZoneToZone {
	
	private String fromZone;
	private String toZone;
	
	
	/*
	 * public FromZoneToZone(String fromZone, String toZone) { if( fromZone == null
	 * || this.) this.fromZone = fromZone; this.toZone = toZone; }
	 * 
	 * @Override public boolean equals(Object obj) {
	 * 
	 * boolean isEqual = false;
	 * 
	 * if( obj instanceof FromZoneToZone) { FromZoneToZone sourceFromZoneToZoneObj =
	 * (FromZoneToZone)obj;
	 * 
	 * if(sourceFromZoneToZoneObj.)
	 * 
	 * }
	 * 
	 * return isEqual; }
	 */

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
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
