package iplanalyser;

public class IplAnalyserFactory {
    public static <E> IplAdapter loadIplData(IndianPremierLeague.Cricket cricket) throws IplAnalyserException {
        if (cricket.equals(IndianPremierLeague.Cricket.BATSMAN))
            return new BattingBowlingAdapter();
        else if (cricket.equals(IndianPremierLeague.Cricket.BOWLER))
            return new BowlingAdapter();
        throw new IplAnalyserException("Invalid field", IplAnalyserException.ExceptionType.INVALID_FIELD);
    }
}
