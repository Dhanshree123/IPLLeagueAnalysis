package com.capgemini.iplLeagueAnalysis;

public class IPLAnalyserException extends Exception {
	enum ExceptionType {
		WRONG_FILE_PATH, WRONG_FILE_TYPE, WRONG_DELIMITER_TYPE, WRONG_HEADER;
	}

	ExceptionType type;

	public IPLAnalyserException(ExceptionType type) {

		this.type = type;
	}

	public IPLAnalyserException(String message, ExceptionType type) {
		super(message);
		this.type = type;

	}

	public ExceptionType getExceptionType() {
		return type;
	}
}