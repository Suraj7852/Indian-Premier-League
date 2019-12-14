package iplanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IndianPremierLeagueTest {
    private static final String IPL_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_WRONG_HEADER_FILE_PATH = "./src/test/resources/IPLRunsWrongHeader.csv";
    IndianPremierLeague premierLeague = new IndianPremierLeague();
    @Test
    public void givenIplDataFile_ReturnsPlayerWith_BestBattingAvg() {
        try {
            Map<String, IplDTO> stringIplDTOMap = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            Assert.assertEquals(100,stringIplDTOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_ifWrongFilePathSent_ThrowsException() {
        try {
            premierLeague.loadIplData(IPL_CSV_WRONG_FILE_PATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.FILE_PROBLEM,e.type);
        }
    }

    @Test
    public void givenIplDataFile_ifWrongHeader_ThrowsException() {
        try {
            premierLeague.loadIplData(IPL_CSV_WRONG_HEADER_FILE_PATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_MISMATCH,e.type);
        }
    }

    @Test
    public void givenIplDataFile_forTopBattingAverage() {
        try {
            Map<String, IplDTO> loadIplData = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            premierLeague.sortByAverage(loadIplData);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }
}
