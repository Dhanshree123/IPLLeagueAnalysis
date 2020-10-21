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

	public List<MostRunCSV> getTopStrikeRate(String csvFilePath) throws CSVException {
		if (mostRunList == null) {
			throw new CSVException("CSV File Builder, not returned list", CSVException.ExceptionType.CSV_ERROR);
		} else if (mostRunList.size() == 0) {
			throw new CSVException("List is empty", CSVException.ExceptionType.NO_CSV_DATA);
		}

		List<MostRunCSV> sortedStrikeRateList = mostRunList.stream()
				.sorted((player1, player2) -> Double.compare(player1.sr, player2.sr)).collect(Collectors.toList());
		Collections.reverse(sortedStrikeRateList);

		return sortedStrikeRateList;
	}

	public List<MostRunCSV> getTop6sCricketer(String mostRunCsvFile) throws CSVException {
		if (mostRunList == null) {
			throw new CSVException("CSV File Builder, not returned list", CSVException.ExceptionType.CSV_ERROR);
		} else if (mostRunList.size() == 0) {
			throw new CSVException("List is empty", CSVException.ExceptionType.NO_CSV_DATA);
		}
		List<MostRunCSV> max6sList = mostRunList.stream()
				.sorted((player1, player2) -> Integer.compare(player1.num6s, player2.num6s))
				.collect(Collectors.toList());
		Collections.reverse(max6sList);

		return max6sList;
	}

	public List<MostRunCSV> getTop4sCricketer(String mostRunCsvFile) throws CSVException {
		if (mostRunList == null) {
			throw new CSVException("CSV File Builder, not returned list", CSVException.ExceptionType.CSV_ERROR);
		} else if (mostRunList.size() == 0) {
			throw new CSVException("List is empty", CSVException.ExceptionType.NO_CSV_DATA);
		}
		List<MostRunCSV> max4sList = mostRunList.stream()
				.sorted((player1, player2) -> Integer.compare(player1.num4s, player2.num4s))
				.collect(Collectors.toList());
		Collections.reverse(max4sList);

		return max4sList;
	}

	public List<MostRunCSV> getBestStrikeRateWith6sAnd4s(String mostRunCsvFile) throws CSVException {
		if (mostRunList == null) {
			throw new CSVException("CSV File Builder, not returned list", CSVException.ExceptionType.CSV_ERROR);
		} else if (mostRunList.size() == 0) {
			throw new CSVException("List is empty", CSVException.ExceptionType.NO_CSV_DATA);
		}
		int mostNumBoundaries = mostRunList.stream().map(i -> i.num4s + i.num6s).max(Integer::compare).get();
		List<MostRunCSV> max4sAnd6sList = mostRunList.stream().filter(i -> i.num4s + i.num6s == mostNumBoundaries)
				.collect(Collectors.toList());

		double HighestStrikeRate = max4sAnd6sList.stream().map(i -> i.sr).max(Double::compare).get();

		List<MostRunCSV> maxStrikeRateList = max4sAnd6sList.stream().filter(i -> i.sr == HighestStrikeRate)
				.collect(Collectors.toList());

		return maxStrikeRateList;
	}
}
