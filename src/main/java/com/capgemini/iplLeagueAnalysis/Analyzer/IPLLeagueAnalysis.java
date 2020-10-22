package com.capgemini.iplLeagueAnalysis.Analyzer;

import java.io.IOException;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.iplLeagueAnalysis.Exceptions.IPLAnalyserException;
import com.capgemini.iplLeagueAnalysis.Pojo.MostRunBatsmen;
import com.capgemini.iplLeagueAnalysis.Pojo.MostWicketBowler;
import com.capgemini.openCSVBuilder.CSVBuilderFactory;
import com.capgemini.openCSVBuilder.CSVException;
import com.capgemini.openCSVBuilder.ICSVBuilder;

public class IPLLeagueAnalysis {
	List<MostRunBatsmen> mostRunList = null;
	List<MostWicketBowler> mostWicketList = null;

	public int loadMostRunsData(String csvFilePath) throws CSVException, IPLAnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			mostRunList = csvBuilder.getCsvFileList(reader, MostRunBatsmen.class);
			return mostRunList.size();
		} catch (NullPointerException | IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (CSVException e) {
			throw new CSVException(e.getMessage(), e.type);
		}
	}

	public int loadMostWicketsData(String csvFilePath) throws IPLAnalyserException, CSVException {
		try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			mostWicketList = csvBuilder.getCsvFileList(reader, MostWicketBowler.class);
			return mostWicketList.size();
		} catch (NullPointerException | IOException e) {
			throw new IPLAnalyserException(e.getMessage(), IPLAnalyserException.ExceptionType.WRONG_FILE_PATH);
		} catch (CSVException e) {
			throw new CSVException(e.getMessage(), e.type);
		}
	}

	public void checkBatsMenCustomExceptions() throws CSVException {
		if (mostRunList == null) {
			throw new CSVException("CSV File Builder, not returned list", CSVException.ExceptionType.CSV_ERROR);
		} else if (mostRunList.size() == 0) {
			throw new CSVException("List is empty", CSVException.ExceptionType.NO_CSV_DATA);
		}

	}

	public void checkBowlerCustomExceptions() throws CSVException {
		if (mostWicketList == null) {
			throw new CSVException("CSV File Builder, not returned list", CSVException.ExceptionType.CSV_ERROR);
		} else if (mostWicketList.size() == 0) {
			throw new CSVException("List is empty", CSVException.ExceptionType.NO_CSV_DATA);
		}

	}

	public List<MostRunBatsmen> getTopBatsmenAverages(String csvFilePath) throws CSVException {
		checkBatsMenCustomExceptions();
		List<MostRunBatsmen> sortedAvgList = mostRunList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getAverage(), player2.getAverage()))
				.collect(Collectors.toList());
		Collections.reverse(sortedAvgList);

		return sortedAvgList;
	}

	public List<MostRunBatsmen> getTopBatsmenStrikeRate(String csvFilePath) throws CSVException {
		checkBatsMenCustomExceptions();
		List<MostRunBatsmen> sortedStrikeRateList = mostRunList.stream()
				.sorted((player1, player2) -> Double.compare(player1.sr, player2.sr)).collect(Collectors.toList());
		Collections.reverse(sortedStrikeRateList);

		return sortedStrikeRateList;
	}

	public List<MostRunBatsmen> getTop6sCricketer(String mostRunCsvFile) throws CSVException {
		checkBatsMenCustomExceptions();
		List<MostRunBatsmen> max6sList = mostRunList.stream()
				.sorted((player1, player2) -> Integer.compare(player1.num6s, player2.num6s))
				.collect(Collectors.toList());
		Collections.reverse(max6sList);

		return max6sList;
	}

	public List<MostRunBatsmen> getTop4sCricketer(String mostRunCsvFile) throws CSVException {
		checkBatsMenCustomExceptions();
		List<MostRunBatsmen> max4sList = mostRunList.stream()
				.sorted((player1, player2) -> Integer.compare(player1.num4s, player2.num4s))
				.collect(Collectors.toList());
		Collections.reverse(max4sList);

		return max4sList;
	}

	public List<MostRunBatsmen> getBestStrikeRateWith6sAnd4s(String mostRunCsvFile) throws CSVException {
		checkBatsMenCustomExceptions();
		int mostNumBoundaries = mostRunList.stream().map(i -> i.num4s + i.num6s).max(Integer::compare).get();
		List<MostRunBatsmen> max4sAnd6sList = mostRunList.stream().filter(i -> i.num4s + i.num6s == mostNumBoundaries)
				.collect(Collectors.toList());

		double highestStrikeRate = max4sAnd6sList.stream().map(i -> i.sr).max(Double::compare).get();

		List<MostRunBatsmen> maxStrikeRateList = max4sAnd6sList.stream().filter(i -> i.sr == highestStrikeRate)
				.collect(Collectors.toList());

		return maxStrikeRateList;
	}

	public List<MostRunBatsmen> getGreatAverageWithBestStrikeRates(String mostRunCsvFile) throws CSVException {
		checkBatsMenCustomExceptions();
		Double highestAverage = mostRunList.stream().map(i -> i.getAverage()).max(Double::compare).get();
		List<MostRunBatsmen> maxAverageList = mostRunList.stream().filter(i -> i.getAverage() == highestAverage)
				.collect(Collectors.toList());

		double highestStrikeRate = maxAverageList.stream().map(i -> i.sr).max(Double::compare).get();

		List<MostRunBatsmen> maxStrikeRateList = maxAverageList.stream().filter(i -> i.sr == highestStrikeRate)
				.collect(Collectors.toList());

		return maxStrikeRateList;
	}

	public List<MostRunBatsmen> getCricketersWithMaximumRunWithBestAverages(String mostRunCsvFile) throws CSVException {
		checkBatsMenCustomExceptions();
		int maximumRuns = mostRunList.stream().map(i -> i.runs).max(Integer::compare).get();
		List<MostRunBatsmen> maxRunsList = mostRunList.stream().filter(i -> i.runs == maximumRuns)
				.collect(Collectors.toList());

		double highestAverage = maxRunsList.stream().map(i -> i.getAverage()).max(Double::compare).get();

		List<MostRunBatsmen> maxAvgList = maxRunsList.stream().filter(i -> i.getAverage() == highestAverage)
				.collect(Collectors.toList());

		return maxAvgList;
	}

	public List<MostWicketBowler> getTopBowlerAverages(String mostWicketCsvFile) throws CSVException {
		checkBowlerCustomExceptions();
		List<MostWicketBowler> sortedAvgList = mostWicketList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getAverage(), player2.getAverage()))
				.collect(Collectors.toList());
		return sortedAvgList;
	}

	public List<MostWicketBowler> getTopBowlerStrikeRate(String mostWicketCsvFile) throws CSVException {
		checkBowlerCustomExceptions();
		List<MostWicketBowler> sortedStrikeRateList = mostWicketList.stream()
				.sorted((player1, player2) -> Double.compare(player1.getSR(), player2.getSR()))
				.collect(Collectors.toList());
		return sortedStrikeRateList;
	}

}
