package com.sahaj.assignment.tigerCard;

import com.sahaj.assignment.tigerCard.inputReader.FileInputReader;
import com.sahaj.assignment.tigerCard.pojos.PeakHourTimingsMasterManager;
import com.sahaj.assignment.tigerCard.pojos.ZoneTravelMasterManager;
import com.sahaj.assignment.tigerCard.visitables.JourneyNode;
import com.sahaj.assignment.tigerCard.visitors.FareCalculatorVisitor;

public class RunTigerCardApp {

	public static final String INPUT_FOLDER_NAME = "input";
	public static final String PEAK_HOUR_TIMING_MASTER_FILE_NAME = "peakHourMaster.txt";
	public static final String ZONE_FARE_MASTER_FILE_NAME = "zoneFareMaster.txt";
	public static final String INPUT_JOURNEYS_FILE_NAME = "inputjourneys.txt";
	
	
	public static void main(String[] args) {
		
		String pathSeperator = System.getProperty("file.separator");
		String inputFileFolder = System.getProperty("user.dir") + pathSeperator + INPUT_FOLDER_NAME + pathSeperator;
		
		
		PeakHourTimingsMasterManager.INSTANCE.initializePeakHourTimingMaster(inputFileFolder, PEAK_HOUR_TIMING_MASTER_FILE_NAME);
		ZoneTravelMasterManager.INSTANCE.initializeZoneFareMasterData(inputFileFolder, ZONE_FARE_MASTER_FILE_NAME);
		
		
		JourneyNode iJourneyNode = FileInputReader.INSTANCE.readInput(inputFileFolder, INPUT_JOURNEYS_FILE_NAME);
		
		double totalJourneyFare = iJourneyNode.accept(new FareCalculatorVisitor());
		System.out.println("Printing journeyFare::" + totalJourneyFare);
		
	
	}
	
}
