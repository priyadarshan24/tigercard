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
		
		TravelTime travelTime = new TravelTime("Monday", "10:20");
		SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone21).travelTime(travelTime).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Monday", "10:45");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Monday", "16:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Monday", "18:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Monday", "19:00");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).travelTime(travelTime).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		double dayJourneyFare = dayJourneyNode.accept(new FareCalculatorVisitor());

		assertEquals("Day Journey Fare Calculation Failed", dayJourneyFare, 120,0);
		
	}
	
	
	@Test
	public void testVisitDayJourneyNodeDailyCapNotReached() 
	{
		DayJourneyNode dayJourneyNode = new DayJourneyNode("Monday");
		
		TravelTime travelTime = new TravelTime("Sunday", "10:20");
		SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone21).travelTime(travelTime).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Sunday", "10:45");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Saturday", "16:00");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).travelTime(travelTime).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		double dayJourneyFare = dayJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Day Journey Fare Calculation Failed", dayJourneyFare, 95,0);
		
	}

	@Test
	public void testVisitWeekJourneyNode() {
		
		WeekJourneyNode weekJourneyNode = new WeekJourneyNode();
		
		DayJourneyNode dayJourneyNode1 = new DayJourneyNode("Monday");
		
		TravelTime travelTime = new TravelTime("Monday", "10:20");
		SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone21).travelTime(travelTime).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
	
		travelTime = new TravelTime("Monday", "10:45");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Monday", "16:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Monday", "18:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Monday", "19:00");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).travelTime(travelTime).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode1);
		
		DayJourneyNode dayJourneyNode2 = new DayJourneyNode("Tuesday");
		
		travelTime = new TravelTime("Tuesday", "10:20");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone21).travelTime(travelTime).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Tuesday", "10:45");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Tuesday", "16:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Tuesday", "18:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Tuesday", "19:00");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).travelTime(travelTime).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode2);
		
		DayJourneyNode dayJourneyNode3 = new DayJourneyNode("Wednesday");
		
		travelTime = new TravelTime("Wednesday", "10:20");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone21).travelTime(travelTime).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Wednesday", "10:45");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Wednesday", "16:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Wednesday", "18:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Wednesday", "19:00");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).travelTime(travelTime).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode3);
		
		DayJourneyNode dayJourneyNode4 = new DayJourneyNode("Thursday");
		
		travelTime = new TravelTime("Thursday", "10:20");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone21).travelTime(travelTime).build();
		dayJourneyNode4.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Thursday", "10:45");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode4.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Thursday", "16:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode4.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Thursday", "18:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode4.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Thursday", "19:00");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).travelTime(travelTime).build();
		dayJourneyNode4.addSingleJourneyNode(singleJourneyNode);
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode4);
		
		DayJourneyNode dayJourneyNode5 = new DayJourneyNode("Saturday");
		
		travelTime = new TravelTime("Saturday", "10:20");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone21).travelTime(travelTime).build();
		dayJourneyNode5.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Saturday", "10:45");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode5.addSingleJourneyNode(singleJourneyNode);
	
		travelTime = new TravelTime("Saturday", "16:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode5.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Saturday", "18:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		dayJourneyNode5.addSingleJourneyNode(singleJourneyNode);
		
		travelTime = new TravelTime("Saturday", "19:00");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).travelTime(travelTime).build();
		dayJourneyNode5.addSingleJourneyNode(singleJourneyNode);
		
		weekJourneyNode.addDayJourneyNode(dayJourneyNode5);
		
	}

	@Test
	public void testVisitOverallJourneyNode() {
		//fail("Not yet implemented");
	}

	@Test
	public void testVisitSingleJourneyNode() {
		
		TravelTime travelTime = new TravelTime("Monday", "10:20");
		SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone21).travelTime(travelTime).build();
		double singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 35,0);
		
		travelTime = new TravelTime("Monday", "10:45");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 25,0);
		
		
		travelTime = new TravelTime("Monday", "16:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 25,0);
		
		
		
		travelTime = new TravelTime("Sunday", "09:15");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).travelTime(travelTime).build();
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 30,0);
	
		travelTime = new TravelTime("Monday", "11:20");
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).travelTime(travelTime).build();
		singleJourneyFare = singleJourneyNode.accept(new FareCalculatorVisitor());
		assertEquals("Single Journey Fare Calculation Failed", singleJourneyFare, 30,0);

		
	}

}
