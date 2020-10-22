package com.capgemini.iplLeagueAnalysis;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capgemini.iplLeagueAnalysis.Analyzer.IPLLeagueAnalysis;
import com.capgemini.iplLeagueAnalysis.Exceptions.IPLAnalyserException;
import com.capgemini.iplLeagueAnalysis.Pojo.MostRunBatsmen;
import com.capgemini.iplLeagueAnalysis.Pojo.MostWicketBowler;
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
		List<MostRunBatsmen> averageListTopBatsmen = iplLeagueAnalysis.getTopBatsmenAverages(MOST_RUN_CSV_FILE);
		Assert.assertEquals(83.2, averageListTopBatsmen.get(0).getAverage(), 0.0);
		Assert.assertEquals(69.2, averageListTopBatsmen.get(1).getAverage(), 0.0);
		Assert.assertEquals(56.66, averageListTopBatsmen.get(2).getAverage(), 0.0);
	}

	@Test
	public void givenMostRunCSVFileReturnsTop3BatsmenStrikeRates() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunBatsmen> listOfTopStrikeRate = iplLeagueAnalysis.getTopBatsmenStrikeRate(MOST_RUN_CSV_FILE);
		Assert.assertEquals(333.33, listOfTopStrikeRate.get(0).sr, 0.0);
		Assert.assertEquals(204.81, listOfTopStrikeRate.get(1).sr, 0.0);
		Assert.assertEquals(200.00, listOfTopStrikeRate.get(2).sr, 0.0);
	}

	@Test
	public void givenMostRunCSVFileReturnsCricketerWithMax6s() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunBatsmen> listOfTopStrikeRate = iplLeagueAnalysis.getTop6sCricketer(MOST_RUN_CSV_FILE);
		Assert.assertEquals("Andre Russell", listOfTopStrikeRate.get(0).player);
	}

	@Test
	public void givenMostRunCSVFileReturnsCricketerWithMax4s() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunBatsmen> listOfTopStrikeRate = iplLeagueAnalysis.getTop4sCricketer(MOST_RUN_CSV_FILE);
		Assert.assertEquals("Shikhar Dhawan", listOfTopStrikeRate.get(0).player);
	}

	@Test
	public void givenMostRunCSVFileReturnsBestStrikeRatesWith6sAnd4s() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunBatsmen> listOfTopStrikeRate = iplLeagueAnalysis.getBestStrikeRateWith6sAnd4s(MOST_RUN_CSV_FILE);
		Assert.assertEquals("Andre Russell", listOfTopStrikeRate.get(0).player);
	}

	@Test
	public void givenMostRunCSVFileReturnsCricketersWithGreatAverageWithBestStrikeRates()
			throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunBatsmen> listOfTopAverage = iplLeagueAnalysis.getGreatAverageWithBestStrikeRates(MOST_RUN_CSV_FILE);
		Assert.assertEquals("MS Dhoni", listOfTopAverage.get(0).player);
	}

	@Test
	public void givenMostRunCSVFileReturnsCricketersWithMaximumRunWithBestAverages()
			throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunBatsmen> listOfMaxRun = iplLeagueAnalysis
				.getCricketersWithMaximumRunWithBestAverages(MOST_RUN_CSV_FILE);
		Assert.assertEquals("David Warner", listOfMaxRun.get(0).player);
	}

	@Test
	public void givenMostRunCSVFileReturnsTop3BowlingAverages() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostWicketsData(MOST_WICKET_CSV_FILE);
		List<MostWicketBowler> averageList = iplLeagueAnalysis.getTopBowlerAverages(MOST_WICKET_CSV_FILE);
		Assert.assertEquals(11.0, averageList.get(0).getAverage(), 0.0);
		Assert.assertEquals(14.0, averageList.get(1).getAverage(), 0.0);
		Assert.assertEquals(14.5, averageList.get(2).getAverage(), 0.0);
	}

	@Test
	public void givenMostRunCSVFileReturnsTop3BowlingStrikeRates() throws IPLAnalyserException, CSVException {
		iplLeagueAnalysis.loadMostWicketsData(MOST_WICKET_CSV_FILE);
		List<MostWicketBowler> listOfTopStrikeRate = iplLeagueAnalysis.getTopBowlerStrikeRate(MOST_WICKET_CSV_FILE);
		Assert.assertEquals(8.66, listOfTopStrikeRate.get(0).getSR(), 0.0);
		Assert.assertEquals(10.75, listOfTopStrikeRate.get(1).getSR(), 0.0);
		Assert.assertEquals(11.00, listOfTopStrikeRate.get(2).getSR(), 0.0);
	}

}
