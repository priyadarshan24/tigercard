package com.sahaj.assignment.tigerCard.pojos;

import static org.junit.Assert.*;

import org.junit.Test;

public class FromZoneToZoneTest {

	@Test
	public void testEqualsObject() {
		
		//Compare One way Journey
		FromZoneToZone toCompareWith = new FromZoneToZone("1", "2");
		FromZoneToZone toCompare = new FromZoneToZone("1", "2");
		assertTrue("One way Zone matching failed", toCompareWith.equals(toCompare));
		
		
		//Compare reverse way Journey
		toCompareWith = new FromZoneToZone("1", "2");
		toCompare = new FromZoneToZone("2", "1");
		assertTrue("Reverse way Zone matching failed", toCompareWith.equals(toCompare));
		
		//Compare one way Journey with space
		toCompareWith = new FromZoneToZone("2 ", "1");
		toCompare = new FromZoneToZone("1", "2 ");
		assertTrue("Zone matching with space character failed", toCompareWith.equals(toCompare));
		
		//Testing scenario where the zone journeys dont match
		toCompareWith = new FromZoneToZone("3", "1");
		toCompare = new FromZoneToZone("1", "2 ");
		assertFalse("Different zone journeys incorrectly matched", toCompareWith.equals(toCompare));
		
		
		
	}

}
