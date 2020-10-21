package com.capgemini.iplLeagueAnalysis;

import java.io.IOException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import com.capgemini.openCSVBuilder.CSVBuilderFactory;
import com.capgemini.openCSVBuilder.CSVException;
import com.capgemini.openCSVBuilder.ICSVBuilder;

public class IPLLeagueAnalysis {
	List<MostRunCSV> mostRunList = null;
	List<MostWicketCSV> mostWicketList = null;

	public int loadMostRunsData(String csvFilePath) throws CSVException, IPLAnalyserException {
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			mostRunList = csvBuilder.getCsvFileList(reader, MostRunCSV.class);
			return mostRunList.size();
		} catch (NullPointerException | IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (CSVException e) {
			throw new CSVException(e.getMessage(), e.type);
		}
	}

	public int loadMostWicketsData(String csvFilePath) throws IPLAnalyserException, CSVException {
		Reader reader;
		try {
			reader = Files.newBufferedReader(Paths.get(csvFilePath));
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			mostWicketList = csvBuilder.getCsvFileList(reader, MostWicketCSV.class);
			return mostWicketList.size();
		} catch (NullPointerException | IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (CSVException e) {
			throw new CSVException(e.getMessage(), e.type);
		}
	}

	public List<MostRunCSV> getTopBattingAverages(String csvFilePath) throws CSVException {
		if (mostRunList == null) {
			throw new CSVException("CSV File Builder, not returned list", CSVException.ExceptionType.CSV_ERROR);
		} else if (mostRunList.size() == 0) {
			throw new CSVException("List is empty", CSVException.ExceptionType.NO_CSV_DATA);
		}

		List<MostRunCSV> sortedAvgList = mostRunList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getAverage(), player2.getAverage()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);

		return sortedAvgList;
	}
}
