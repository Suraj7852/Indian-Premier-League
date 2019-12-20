package iplanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IndianPremierLeagueTest {
    private static final String IPL_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_WRONG_FILE_PATH = "./src/main/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_WRONG_HEADER_FILE_PATH = "./src/test/resources/IPLRunsWrongHeader.csv";
    private static final String IPL_CSV_DEMO_FILE_PATH = "./src/test/resources/IplDemo.csv";
    private static final String IPL_CSV_BOWLING_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    IndianPremierLeague premierLeague = new IndianPremierLeague();
    @Test
    public void givenIplDataFile_ReturnsPlayerWith_BestBattingAvg() {
        try {
            Map<String, IplDAO> stringIplDTOMap = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
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
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData,IplFields.AVERAGE);
            Assert.assertEquals("MS Dhoni",average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopStrikeRate() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData,IplFields.STRIKE_RATE);
            Assert.assertEquals("Ishant Sharma",average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_WhoHitMat_SixAndFour() {
        try {
            Map<String, IplDAO> daoMap = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            String maxFourSixPlayer = premierLeague.maxFourSix(daoMap, IplFields.SIX);
            Assert.assertEquals("Andre Russell",maxFourSixPlayer);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_strikeRateBasedOnFourAndSix() {
        try {
            Map<String, IplDAO> iplDAOMap = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            String rate = premierLeague.strikeRateOfSixAndFour(iplDAOMap, IplFields.SIX);
            Assert.assertEquals("Kagiso Rabada",rate);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_greatAverageWith_greatStrikingRate() {
        try {
            Map<String, IplDAO> iplDAOMap = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            IplDAO[] rate = premierLeague.greatAverageWithGreatStrikeRate(iplDAOMap, IplFields.AVG_WITH_SR);
            Assert.assertEquals("David Warner",rate[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_maximumRunWith_greatAverage() {
        try {
            Map<String, IplDAO> iplDAOMap = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            IplDAO[] rate = premierLeague.greatAverageWithGreatStrikeRate(iplDAOMap, IplFields.RUN_WITH_AVG);
            Assert.assertEquals("David Warner",rate[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopBowlingAverage() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplWicketData(IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData,IplFields.AVERAGE);
            Assert.assertEquals("MS Dhoni",average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }
}
