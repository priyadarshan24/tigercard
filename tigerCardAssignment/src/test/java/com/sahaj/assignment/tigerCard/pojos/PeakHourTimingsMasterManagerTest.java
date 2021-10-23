package com.sahaj.assignment.tigerCard.pojos;

import java.util.List;

import org.junit.Test;

public class PeakHourTimingsMasterManagerTest {

	@Test(expected = UnsupportedOperationException.class)
	public void testGetWeekDayPeakHourTimings() {
		
		List<PeakHourTimings> peakHourTimings = PeakHourTimingsMasterManager.INSTANCE.getWeekDayPeakHourTimings();
		peakHourTimings.add(new PeakHourTimings("10:15", "20:20"));
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testGetWeekendPeakHourTimings() {
		List<PeakHourTimings> peakHourTimings = PeakHourTimingsMasterManager.INSTANCE.getWeekendPeakHourTimings();
		peakHourTimings.add(new PeakHourTimings("10:15", "20:20"));
	}
}
