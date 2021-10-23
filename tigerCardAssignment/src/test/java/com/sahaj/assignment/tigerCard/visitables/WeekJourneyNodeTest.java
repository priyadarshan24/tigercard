package com.sahaj.assignment.tigerCard.visitables;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;

public class WeekJourneyNodeTest {
	
	private FromZoneToZone fromZoneToZone11;
	private FromZoneToZone fromZoneToZone12;
	private FromZoneToZone fromZoneToZone13;
	private FromZoneToZone fromZoneToZone22;
	private FromZoneToZone fromZoneToZone33;
	private FromZoneToZone fromZoneToZone23;
	
	@Before
	public void initialize()
	{
		 fromZoneToZone11 = new FromZoneToZone("1", "1");
		 fromZoneToZone12 = new FromZoneToZone("1", "2");
		 fromZoneToZone13 = new FromZoneToZone("1", "3");
		 fromZoneToZone22 = new FromZoneToZone("2", "2");
		 fromZoneToZone33 = new FromZoneToZone("3", "3");
		 fromZoneToZone23 = new FromZoneToZone("2", "3");
		 
		 
		ZoneTravelMaster zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().fromZone(fromZoneToZone11).dailyCapFare(100).build();
		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone11, zoneTravelMaster);
		
		zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().fromZone(fromZoneToZone12).dailyCapFare(120).build();
		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone12, zoneTravelMaster);
		
		zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().fromZone(fromZoneToZone13).dailyCapFare(140).build();
		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone13, zoneTravelMaster);
		
		zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().fromZone(fromZoneToZone22).dailyCapFare(90).build();
		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone22, zoneTravelMaster);
		
		zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().fromZone(fromZoneToZone33).dailyCapFare(100).build();
		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone33, zoneTravelMaster);
		
		zoneTravelMaster = new ZoneTravelMaster.ZoneTravelMasterBuilder().fromZone(fromZoneToZone23).dailyCapFare(130).build();
		ZoneTravelMasterManager.INSTANCE.addZoneTravelMaster(fromZoneToZone23, zoneTravelMaster);
	
		
	}

	@Test
	public void testGetFarthestTravelZoneDuringWeek() 
	{
		DayJourneyNode dayJourneyNode1 = new DayJourneyNode("Monday");
		
		SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone23).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		
		
		DayJourneyNode dayJourneyNode2 = new DayJourneyNode("Tuesday");
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone33).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone22).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
		
		
		DayJourneyNode dayJourneyNode3 = new DayJourneyNode("Wednesday");
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone22).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone23).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		
		WeekJourneyNode weekJourneyNode = new WeekJourneyNode();
		weekJourneyNode.addDayJourneyNode(dayJourneyNode1);
		weekJourneyNode.addDayJourneyNode(dayJourneyNode2);
		weekJourneyNode.addDayJourneyNode(dayJourneyNode3);
		
		
		ZoneTravelMaster farthestTravelZone = weekJourneyNode.getFarthestTravelZoneDuringWeek();
		
		assertTrue("Farthest Travel zone incorrectly identified" , farthestTravelZone.getFromZoneToZone().equals(fromZoneToZone23) );
		
	}
	
	
	@Test
	public void testGetFarthestTravelZoneDuringWeek2() 
	{
		DayJourneyNode dayJourneyNode1 = new DayJourneyNode("Monday");
		
		SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone23).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).build();
		dayJourneyNode1.addSingleJourneyNode(singleJourneyNode);
		
		
		
		DayJourneyNode dayJourneyNode2 = new DayJourneyNode("Tuesday");
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone13).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone33).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone22).build();
		dayJourneyNode2.addSingleJourneyNode(singleJourneyNode);
		
		
		DayJourneyNode dayJourneyNode3 = new DayJourneyNode("Wednesday");
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone13).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone22).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone23).build();
		dayJourneyNode3.addSingleJourneyNode(singleJourneyNode);
		
		
		WeekJourneyNode weekJourneyNode = new WeekJourneyNode();
		weekJourneyNode.addDayJourneyNode(dayJourneyNode1);
		weekJourneyNode.addDayJourneyNode(dayJourneyNode2);
		weekJourneyNode.addDayJourneyNode(dayJourneyNode3);
		
		
		ZoneTravelMaster farthestTravelZone = weekJourneyNode.getFarthestTravelZoneDuringWeek();
		
		assertTrue("Farthest Travel zone incorrectly identified" , farthestTravelZone.getFromZoneToZone().equals(fromZoneToZone13) );
		
	}

}
