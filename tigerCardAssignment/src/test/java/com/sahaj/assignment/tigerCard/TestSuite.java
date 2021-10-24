package com.sahaj.assignment.tigerCard;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZoneTest;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimingsMasterManagerTest;
import com.sahaj.assignment.tigerCard.pojos.TravelTimeTest;
import com.sahaj.assignment.tigerCard.utils.TigerCardUtilTest;
import com.sahaj.assignment.tigerCard.visitables.DayJourneyNodeTest;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNodeTest;
import com.sahaj.assignment.tigerCard.visitors.FareCalculatorVisitorTest;

@RunWith(Suite.class)
@SuiteClasses({
	FromZoneToZoneTest.class,
	PeakHourTimingsMasterManagerTest.class,
	TravelTimeTest.class,
	TigerCardUtilTest.class,
	DayJourneyNodeTest.class,
	WeekJourneyNodeTest.class,
	FareCalculatorVisitorTest.class
})
public class TestSuite {

}
