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
	public void testIsPeakHourTravelTime() {
		
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

}
