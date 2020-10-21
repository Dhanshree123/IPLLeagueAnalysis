package com.capgemini.iplLeagueAnalysis;

import org.junit.Assert;
import org.junit.Test;

public class IPLLeagueAnalysisTest {

	private static final String MOST_RUN_CSV_FILE = "./MostRun.csv";
	private static final String MOST_WICKET_CSV_FILE = "./MostWicket.csv";

	@Test
	public void givenMostRunCSVFileReturnsCorrectRecords() {
		IPLLeagueAnalysis iplLeagueAnalysis = new IPLLeagueAnalysis();
		int numOfRecords = iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		Assert.assertEquals(45, numOfRecords);
	}

	@Test
	public void givenMostWicketCSVFileReturnsCorrectRecords() {
		IPLLeagueAnalysis iplLeagueAnalysis = new IPLLeagueAnalysis();
		int numOfRecords = iplLeagueAnalysis.loadMostWicketsData(MOST_WICKET_CSV_FILE);
		Assert.assertEquals(45, numOfRecords);
	}
}
