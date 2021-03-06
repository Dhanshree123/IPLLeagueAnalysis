package com.capgemini.iplLeagueAnalysis.Pojo;

import com.opencsv.bean.CsvBindByName;

public class MostWicketBowler {
	@CsvBindByName(column = "POS")
	public int pos;

	@CsvBindByName(column = "PLAYER")
	public String player;

	@CsvBindByName(column = "Mat")
	public int mat;

	@CsvBindByName(column = "Inns")
	public int inns;

	@CsvBindByName(column = "Ov")
	public double ov;

	@CsvBindByName(column = "Runs")
	public int runs;

	@CsvBindByName(column = "Wkts")
	public int wkts;

	@CsvBindByName(column = "BBI")
	public int bbi;

	@CsvBindByName(column = "Avg")
	public String avg;

	@CsvBindByName(column = "Econ")
	public double econ;

	@CsvBindByName(column = "SR")
	public String sr;

	@CsvBindByName(column = "4w")
	public int num4w;

	@CsvBindByName(column = "5w")
	public int num5w;

	public Double getAverage() {
		if (avg.equals("-"))
			return Double.MAX_VALUE;
		return Double.parseDouble(avg);
	}

	public Double getSR() {
		if (sr.equals("-"))
			return Double.MAX_VALUE;
		return Double.parseDouble(sr);
	}

	@Override
	public String toString() {
		return "MostRunCSV{" + "POS=" + pos + ", Player=" + player + ", Mat=" + mat + ", Inns=" + inns + ", Ov=" + ov
				+ ", Runs=" + runs + ", Wkts=" + wkts + ", BBI=" + bbi + ", Avg=" + avg + ", Econ=" + econ + ", SR="
				+ sr + ", 4w=" + num4w + ", 5w=" + num5w + '}';
	}
}
