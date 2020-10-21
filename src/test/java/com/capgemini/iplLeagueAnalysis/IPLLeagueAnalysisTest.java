package com.capgemini.iplLeagueAnalysis;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.openCSVBuilder.CSVException;

public class IPLLeagueAnalysisTest {

	private static final String MOST_RUN_CSV_FILE = "./MostRun.csv";
	private static final String MOST_WICKET_CSV_FILE = "./MostWicket.csv";

	IPLLeagueAnalysis iplLeagueAnalysis = null;

	@Before
	public void createObject() {
		iplLeagueAnalysis = new IPLLeagueAnalysis();
	}

	@Test
	public void givenMostRunCSVFileReturnsCorrectRecords() throws CSVException, IPLAnalyserException {
		int numOfRecords = iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		Assert.assertEquals(101, numOfRecords);
	}

	@Test
	public void givenMostWicketCSVFileReturnsCorrectRecords() throws IPLAnalyserException, CSVException {
		int numOfRecords = iplLeagueAnalysis.loadMostWicketsData(MOST_WICKET_CSV_FILE);
		Assert.assertEquals(99, numOfRecords);
	}

	@Test
	public void givenMostRunCSVFileReturnsTop3BattingAverages() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunCSV> averageListTopBatsmen = iplLeagueAnalysis.getTopBattingAverages(MOST_RUN_CSV_FILE);
		Assert.assertEquals(83.2, averageListTopBatsmen.get(0).getAverage(), 0.0);
		Assert.assertEquals(69.2, averageListTopBatsmen.get(1).getAverage(), 0.0);
		Assert.assertEquals(56.66, averageListTopBatsmen.get(2).getAverage(), 0.0);
	}

	@Test
	public void givenMostRunCSVFileReturnsTop3StrikeRates() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunCSV> listOfTopStrikeRate = iplLeagueAnalysis.getTopStrikeRate(MOST_RUN_CSV_FILE);
		Assert.assertEquals(333.33, listOfTopStrikeRate.get(0).sr, 0.0);
		Assert.assertEquals(204.81, listOfTopStrikeRate.get(1).sr, 0.0);
		Assert.assertEquals(200.00, listOfTopStrikeRate.get(2).sr, 0.0);
	}

	@Test
	public void givenMostRunCSVFileReturnsCricketerWithMax6s() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunCSV> listOfTopStrikeRate = iplLeagueAnalysis.getTop6sCricketer(MOST_RUN_CSV_FILE);
		Assert.assertEquals("Andre Russell", listOfTopStrikeRate.get(0).player);
	}

	@Test
	public void givenMostRunCSVFileReturnsCricketerWithMax4s() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunCSV> listOfTopStrikeRate = iplLeagueAnalysis.getTop4sCricketer(MOST_RUN_CSV_FILE);
		Assert.assertEquals("Shikhar Dhawan", listOfTopStrikeRate.get(0).player);
	}

}
