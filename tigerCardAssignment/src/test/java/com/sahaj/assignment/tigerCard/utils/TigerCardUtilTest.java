package com.sahaj.assignment.tigerCard.utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class TigerCardUtilTest {

	@Test
	public void testGetTimeinSeconds() {
		
		
		assertEquals("Calculation Error", TigerCardUtil.INSTANCE.getTimeinSeconds("10:50"), 39000);
		
		assertEquals("Boundary Value Testing Error", TigerCardUtil.INSTANCE.getTimeinSeconds("00:50"), 3000);
		
		assertEquals("Boundary Value Testing Error", TigerCardUtil.INSTANCE.getTimeinSeconds("00:00"), 0);
		
		
		
		
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void testGetTimeinSecondsExceptionHandling() {
		
		TigerCardUtil.INSTANCE.getTimeinSeconds("00:00");
		
		TigerCardUtil.INSTANCE.getTimeinSeconds(" ");
		
	}
}
