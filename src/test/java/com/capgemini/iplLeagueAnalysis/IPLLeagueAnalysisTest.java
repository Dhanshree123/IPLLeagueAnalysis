package com.capgemini.iplLeagueAnalysis;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.capgemini.openCSVBuilder.CSVException;

public class IPLLeagueAnalysisTest {

	private static final String MOST_RUN_CSV_FILE = "./MostRun.csv";
	private static final String MOST_WICKET_CSV_FILE = "./MostWicket.csv";

	@Test
	public void givenMostRunCSVFileReturnsCorrectRecords() throws CSVException, IPLAnalyserException {
		IPLLeagueAnalysis iplLeagueAnalysis = new IPLLeagueAnalysis();
		int numOfRecords = iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		Assert.assertEquals(101, numOfRecords);
	}

	@Test
	public void givenMostWicketCSVFileReturnsCorrectRecords() throws IPLAnalyserException, CSVException {
		IPLLeagueAnalysis iplLeagueAnalysis = new IPLLeagueAnalysis();
		int numOfRecords = iplLeagueAnalysis.loadMostWicketsData(MOST_WICKET_CSV_FILE);
		Assert.assertEquals(99, numOfRecords);
	}

	@Test
	public void givenMostRunCSVFileReturnsTop3BattingAverages() throws IPLAnalyserException, CSVException {
		IPLLeagueAnalysis iplLeagueAnalysis = new IPLLeagueAnalysis();
		iplLeagueAnalysis.loadMostRunsData(MOST_RUN_CSV_FILE);
		List<MostRunCSV> averageListTopBatsmen = iplLeagueAnalysis.getTopBattingAverages(MOST_RUN_CSV_FILE);
		Assert.assertEquals(83.2, averageListTopBatsmen.get(0).getAverage(), 0.0);
		Assert.assertEquals(69.2, averageListTopBatsmen.get(1).getAverage(), 0.0);
		Assert.assertEquals(56.66, averageListTopBatsmen.get(2).getAverage(), 0.0);
	}

}
