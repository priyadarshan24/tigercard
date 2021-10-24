package com.sahaj.assignment.tigerCard;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.sahaj.assignment.tigerCard.inputReader.FileInputReader;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimingsMasterManager;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitables.JourneyNode;
import com.sahaj.assignment.tigerCard.visitors.FareCalculatorVisitor;

public class RunTigerCardAppTest {

	private String inputFileFolder;
	
	@Before
	public void setUp() throws Exception {
		
		String pathSeperator = System.getProperty("file.separator");
		inputFileFolder = System.getProperty("user.dir") + pathSeperator + RunTigerCardApp.INPUT_FOLDER_NAME + pathSeperator;
		
		
		PeakHourTimingsMasterManager.INSTANCE.initializePeakHourTimingMaster(inputFileFolder, RunTigerCardApp.PEAK_HOUR_TIMING_MASTER_FILE_NAME);
		ZoneTravelMasterManager.INSTANCE.initializeZoneFareMasterData(inputFileFolder, RunTigerCardApp.ZONE_FARE_MASTER_FILE_NAME);
		
	}

	@Test
	public void testCalculateTotalJourneyFare() {
		
		JourneyNode iJourneyNode = FileInputReader.INSTANCE.readInput(inputFileFolder, RunTigerCardApp.INPUT_JOURNEYS_FILE_NAME);
		double totalJourneyFare = iJourneyNode.accept(new FareCalculatorVisitor());
		
		assertEquals(665, totalJourneyFare, 0);
	}

}
