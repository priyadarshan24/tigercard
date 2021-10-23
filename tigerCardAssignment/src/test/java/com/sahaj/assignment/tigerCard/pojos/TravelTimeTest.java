package com.sahaj.assignment.tigerCard.pojos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TravelTimeTest {
	
	@Before
	public void initialize()
	{
		PeakHourTimings peakHourTimings = new PeakHourTimings("07:00", "10:30");
		PeakHourTimingsMasterManager.INSTANCE.addWeekDayPeakHourTimings(peakHourTimings);
		
		peakHourTimings = new PeakHourTimings("17:00", "20:00");
		PeakHourTimingsMasterManager.INSTANCE.addWeekDayPeakHourTimings(peakHourTimings);
		
		
		
		peakHourTimings = new PeakHourTimings("09:00", "11:00");
		PeakHourTimingsMasterManager.INSTANCE.addWeekendPeakHourTimings(peakHourTimings);
		
		peakHourTimings = new PeakHourTimings("18:00", "22:00");
		PeakHourTimingsMasterManager.INSTANCE.addWeekendPeakHourTimings(peakHourTimings);
		
	
		
	}
	

	@Test
	public void testIsPeakHourTravelTimeForWeekdays() {
		
		TravelTime travelTime = new TravelTime("Monday", "07:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Monday", "08:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Monday", "10:30");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Monday", "10:29");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Monday", "10:31");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Friday", "07:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		travelTime = new TravelTime("Friday", "10:30");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		travelTime = new TravelTime("Thursday", "07:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		travelTime = new TravelTime("Thursday", "10:30");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		travelTime = new TravelTime("Thursday", "08:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		travelTime = new TravelTime("Thursday", "06:59");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		travelTime = new TravelTime("Thursday", "10:31");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		travelTime = new TravelTime("Friday", "20:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Friday", "20:01");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
	}
	
	
	@Test
	public void testIsPeakHourTravelTimeForWeekends() {
		
		TravelTime travelTime = new TravelTime("Saturday", "09:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "11:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "18:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "22:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		//testing newr about boundary value cases
		travelTime = new TravelTime("Saturday", "08:59");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "09:01");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "11:01");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "10:59");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "17:59");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "18:01");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "22:01");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Saturday", "10:59");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		

		
	//Test the boundary value conditions for SUnday
		travelTime = new TravelTime("Sunday", "09:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "11:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "18:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "22:00");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		//testing newr about boundary value cases
		travelTime = new TravelTime("Sunday", "08:59");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "09:01");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "11:01");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "10:59");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "17:59");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "18:01");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "22:01");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		travelTime = new TravelTime("Sunday", "10:59");
		assertTrue("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
		
		travelTime = new TravelTime("Sunday", "00:00");
		assertFalse("Peak hour calculation failed for " + travelTime, travelTime.isPeakHourTravelTime());
		
		
	}

}
