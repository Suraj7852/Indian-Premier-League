package iplanalyser;

public class IplAnalyserException extends Exception{
    enum ExceptionType {
        FILE_PROBLEM,UNABLE_TO_PARSE,NO_CENSUS_DATA,HEADER_MISMATCH, INVALID_COUNTRY, UNABLE_TO_CAPTURE_CSV_HEADER
    }

    public ExceptionType type;

    public IplAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
