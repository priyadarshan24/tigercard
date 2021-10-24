package com.sahaj.assignment.tigerCard.visitors;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimings;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimingsMasterManager;
import com.sahaj.assignment.tigerCard.pojos.TravelTime;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitables.DayJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.OverallJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.SingleJourneyNode;
import com.sahaj.assignment.tigerCard.visitables.WeekJourneyNode;

public class FareCalculatorVisitorTest {
	
	
	private FromZoneToZone fromZoneToZone11;
	private FromZoneToZone fromZoneToZone12;
	private FromZoneToZone fromZoneToZone21;
	private FromZoneToZone fromZoneToZone22;
	private FromZoneToZone fromZoneToZone33;
	private FromZoneToZone fromZoneToZone23;
	
	@Before
	public void initialize()
	{
		 fromZoneToZone11 = new FromZoneToZone("1", "1");
		 fromZoneToZone12 = new FromZoneToZone("1", "2");
		 fromZoneToZone22 = new FromZoneToZone("2", "2");
		 fromZoneToZone21 = new FromZoneToZone("2", "1");
		 
		 
		ZoneTravelMaster zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().
															fromZone(fromZoneToZone11).dailyCapFare(100).
																peakHourFare(30).offPeakHourFare(25).dailyCapFare(100).weeklyCapFare(500).build();
		
		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone11, zoneTravelMaster);
		
		zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().
				fromZone(fromZoneToZone12).dailyCapFare(120).
					peakHourFare(35).offPeakHourFare(30).weeklyCapFare(600).build();

		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone12, zoneTravelMaster);
		
		
		zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().
				fromZone(fromZoneToZone22).dailyCapFare(80).
					peakHourFare(25).offPeakHourFare(20).weeklyCapFare(400).build();

		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone22, zoneTravelMaster);
		
		
		
		
		///////////////////////////////////////////////// Initialize Peak Hour Master ////////////////////////////////////
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
	public void testVisitDayJourneyNodeDailyCapReached() 
	{
		
		DayJourneyNode dayJourneyNode = new DayJourneyNode("Monday");
		
		dayJourneyNode.addSingleJourneyNode(createSingleJourneyNode("Monday", "10:20", fromZoneToZone21));
		dayJourneyNode.addSingleJourneyNode(createSingleJourneyNode("Monday", "10:45", fromZoneToZone11));
		dayJourneyNode.addSingleJourneyNode(createSingleJourneyNode("Monday", "16:15", fromZoneToZone11));
		dayJourneyNode.addSingleJourneyNode(createSingleJourneyNode("Monday", "18:15", fromZoneToZone11));
		dayJourneyNode.addSingleJourneyNode(createSingleJourneyNode("Monday", "19:00", fromZoneToZone12));
		
		double dayJourneyFare = dayJourneyNode.accept(new FareCalculatorVisitor());

		assertEquals("Day Journey Fare Calculation Failed", dayJourneyFare, 120,0);
		
	}
	
	
	@Test
	public void testVisitDayJourneyNodeDailyCapNotReached() 
	{
	
		DayJourneyNode dayJourneyNode = new DayJourneyNode("Sunday");
		dayJourneyNode.addSingleJourneyNode(createSingleJourneyNode("Sunday", "10:20", fromZoneToZone21));
		dayJourneyNode.addSingleJourneyNode(createSingleJourneyNode("Sunday", "10:45", fromZoneToZone11));
		dayJourneyNode.addSingleJourneyNode(createSingleJourneyNode("Sunday", "16:00", fromZoneToZone12));
		
		double dayJourneyFare = dayJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Day Journey Fare Calculation Failed", dayJourneyFare, 95,0);
		
	}

	@Test
	public void testVisitWeekJourneyNode() {
		
		
		WeekJourneyNode weekJourneyNode = createWeeklyNode();
		
		
		double calculatedFare = weekJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Day Journey Fare Calculation Failed", calculatedFare, 600,0);
		
	}
	
	

	@Test
	public void testVisitOverallJourneyNode() {
		
	
		OverallJourneyNode overallJourneyNode = new OverallJourneyNode();
		overallJourneyNode.addWeekJourneyNode(createWeeklyNode());
		overallJourneyNode.addWeekJourneyNode(createWeeklyNode());
		overallJourneyNode.addWeekJourneyNode(createWeeklyNode());
		overallJourneyNode.addWeekJourneyNode(createWeeklyNode());
		
		double calculatedFare = overallJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Overall Journey Fare Calculation Failed", calculatedFare, 2400,0);
		
	}

	@Test
	public void testVisitSingleJourneyNodeForWeekDays() {
		
		SingleJourneyNode singleJourneyNode = createSingleJourneyNode("Monday", "10:20",fromZoneToZone21);
		double singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 35,0);
		
		singleJourneyNode = createSingleJourneyNode("Monday", "10:45",fromZoneToZone11);
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 25,0);
		
		
		singleJourneyNode = createSingleJourneyNode("Monday", "16:15",fromZoneToZone11);
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 25,0);
		
		
		singleJourneyNode = createSingleJourneyNode("Sunday", "09:15",fromZoneToZone11);
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 30,0);
	
		singleJourneyNode = createSingleJourneyNode("Monday", "11:20",fromZoneToZone12);
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 30,0);

		
	}
	
	@Test
	public void testVisitSingleJourneyNodeForWeekends() {
		
		SingleJourneyNode singleJourneyNode = createSingleJourneyNode("Sunday", "10:20",fromZoneToZone21);
		double singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 35,0);
		
		singleJourneyNode = createSingleJourneyNode("Sunday", "10:45",fromZoneToZone11);
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 30,0);
		
		
		singleJourneyNode = createSingleJourneyNode("Saturday", "16:15",fromZoneToZone22);
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 20,0);
		
		
		singleJourneyNode = createSingleJourneyNode("Saturday", "09:15",fromZoneToZone12);
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 35,0);
	

		
	}

	private SingleJourneyNode createSingleJourneyNode(String day, String time, FromZoneToZone fromZoneToZone) {
		TravelTime travelTime = new TravelTime(day, time);
		SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone).travelTime(travelTime).build();
		return singleJourneyNode;
	}
	
	private WeekJourneyNode createWeeklyNode()
	{
		WeekJourneyNode weekJourneyNode = new WeekJourneyNode();
		DayJourneyNode dayJourneyNode1 = new DayJourneyNode("Monday");
		
		dayJourneyNode1.addSingleJourneyNode(createSingleJourneyNode("Monday", "10:20", fromZoneToZone21));
		dayJourneyNode1.addSingleJourneyNode(createSingleJourneyNode("Monday", "10:45", fromZoneToZone21));
		dayJourneyNode1.addSingleJourneyNode(createSingleJourneyNode("Monday", "16:15", fromZoneToZone11));
		dayJourneyNode1.addSingleJourneyNode(createSingleJourneyNode("Monday", "18:15", fromZoneToZone22));
		dayJourneyNode1.addSingleJourneyNode(createSingleJourneyNode("Monday", "19:00", fromZoneToZone12));
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode1);
		
		
		DayJourneyNode dayJourneyNode2 = new DayJourneyNode("Tuesday");
		dayJourneyNode2.addSingleJourneyNode(createSingleJourneyNode("Tuesday", "11:20", fromZoneToZone21));
		dayJourneyNode2.addSingleJourneyNode(createSingleJourneyNode("Tuesday", "15:45", fromZoneToZone11));
		dayJourneyNode2.addSingleJourneyNode(createSingleJourneyNode("Tuesday", "18:15", fromZoneToZone11));
		dayJourneyNode2.addSingleJourneyNode(createSingleJourneyNode("Tuesday", "22:15", fromZoneToZone22));
		dayJourneyNode2.addSingleJourneyNode(createSingleJourneyNode("Tuesday", "23:00", fromZoneToZone12));
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode2);
		
		DayJourneyNode dayJourneyNode3 = new DayJourneyNode("Wednesday");
		dayJourneyNode3.addSingleJourneyNode(createSingleJourneyNode("Wednesday", "08:20", fromZoneToZone21));
		dayJourneyNode3.addSingleJourneyNode(createSingleJourneyNode("Wednesday", "10:45", fromZoneToZone11));
		dayJourneyNode3.addSingleJourneyNode(createSingleJourneyNode("Wednesday", "13:15", fromZoneToZone11));
		dayJourneyNode3.addSingleJourneyNode(createSingleJourneyNode("Wednesday", "18:15", fromZoneToZone22));
		dayJourneyNode3.addSingleJourneyNode(createSingleJourneyNode("Wednesday", "19:00", fromZoneToZone12));
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode3);
		
	
		DayJourneyNode dayJourneyNode4 = new DayJourneyNode("Thursday");
		
		dayJourneyNode4.addSingleJourneyNode(createSingleJourneyNode("Thursday", "11:20", fromZoneToZone21));
		dayJourneyNode4.addSingleJourneyNode(createSingleJourneyNode("Thursday", "07:45", fromZoneToZone11));
		dayJourneyNode4.addSingleJourneyNode(createSingleJourneyNode("Thursday", "19:15", fromZoneToZone11));
		dayJourneyNode4.addSingleJourneyNode(createSingleJourneyNode("Thursday", "18:15", fromZoneToZone22));
		dayJourneyNode4.addSingleJourneyNode(createSingleJourneyNode("Thursday", "12:00", fromZoneToZone12));
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode4);
		
		DayJourneyNode dayJourneyNode5 = new DayJourneyNode("Saturday");
		
		dayJourneyNode5.addSingleJourneyNode(createSingleJourneyNode("Saturday", "11:20", fromZoneToZone21));
		dayJourneyNode5.addSingleJourneyNode(createSingleJourneyNode("Saturday", "07:45", fromZoneToZone11));
		dayJourneyNode5.addSingleJourneyNode(createSingleJourneyNode("Saturday", "19:15", fromZoneToZone11));
		dayJourneyNode5.addSingleJourneyNode(createSingleJourneyNode("Saturday", "18:15", fromZoneToZone22));
		dayJourneyNode5.addSingleJourneyNode(createSingleJourneyNode("Saturday", "12:00", fromZoneToZone12));
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode5);
		
		
		
		return weekJourneyNode;
	}
	
	
	
	
}
