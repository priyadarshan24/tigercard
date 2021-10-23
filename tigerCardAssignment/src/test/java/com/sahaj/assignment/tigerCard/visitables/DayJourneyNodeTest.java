package com.sahaj.assignment.tigerCard.visitables;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.sahaj.assignment.tigerCard.pojos.FromZoneToZone;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMaster;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;

public class DayJourneyNodeTest {
	
	
	FromZoneToZone fromZoneToZone11;
	FromZoneToZone fromZoneToZone12;
	FromZoneToZone fromZoneToZone13;
	FromZoneToZone fromZoneToZone22;
	FromZoneToZone fromZoneToZone33;
	FromZoneToZone fromZoneToZone23;
	
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
	public void testGetFarthestTravelZone() {
		
		DayJourneyNode dayJourneyNode = new DayJourneyNode.DayJourneyNodeBuilder().build();
		
		SingleJourneyNode singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone11).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone23).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone12).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone13).build();
		//dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone33).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		singleJourneyNode = new SingleJourneyNode.SingleJourneyNodeBuilder().fromZoneToZone(fromZoneToZone22).build();
		dayJourneyNode.addSingleJourneyNode(singleJourneyNode);
		
		FromZoneToZone outputFromZoneToZone = dayJourneyNode.getFarthestTravelZone().getFromZoneToZone();
		
		assertTrue("Farthest Travel zone incorrectly identified" , outputFromZoneToZone.equals(fromZoneToZone23) );
		
		
		
	}

}
