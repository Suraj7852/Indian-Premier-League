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
            Map<String, IplDAO> stringIplDTOMap = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH);
            Assert.assertEquals(100, stringIplDTOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_ifWrongFilePathSent_ThrowsException() {
        try {
            premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_WRONG_FILE_PATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.FILE_PROBLEM, e.type);
        }
    }

    @Test
    public void givenIplDataFile_ifWrongHeader_ThrowsException() {
        try {
            premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_WRONG_HEADER_FILE_PATH);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_MISMATCH, e.type);
        }
    }

    @Test
    public void givenIplDataFile_forTopBattingAverage() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.AVERAGE);
            Assert.assertEquals("MS Dhoni", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopStrikeRate() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.STRIKE_RATE);
            Assert.assertEquals("Ishant Sharma", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_WhoHitMat_SixAndFour() {
        try {
            Map<String, IplDAO> daoMap = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH);
            String maxFourSixPlayer = premierLeague.maxFourSix(daoMap, IplFields.SIX);
            Assert.assertEquals("Andre Russell", maxFourSixPlayer);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_strikeRateBasedOnFourAndSix() {
        try {
            Map<String, IplDAO> iplDAOMap = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH);
            String rate = premierLeague.strikeRateOfSixAndFour(iplDAOMap, IplFields.SIX);
            Assert.assertEquals("Kagiso Rabada", rate);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_greatAverageWith_greatStrikingRate() {
        try {
            Map<String, IplDAO> iplDAOMap = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH);
            IplDAO[] rate = premierLeague.greatAverageWithGreatStrikeRate(iplDAOMap, IplFields.AVG_WITH_SR);
            Assert.assertEquals("MS Dhoni", rate[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_maximumRunWith_greatAverage() {
        try {
            Map<String, IplDAO> iplDAOMap = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH);
            IplDAO[] rate = premierLeague.greatAverageWithGreatStrikeRate(iplDAOMap, IplFields.RUN_WITH_AVG);
            Assert.assertEquals("David Warner", rate[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopBowlingAverage() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.AVERAGE);
            Assert.assertEquals("Umesh Yadav", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopBowlingStrikeRate() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.STRIKE_RATE);
            Assert.assertEquals("Krishnappa Gowtham", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopBowlingEconomyRate() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.ECONOMY);
            Assert.assertEquals("Shivam Dube", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopStrikeRateWith4WicketAnd5Wicket() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.SR_WITH_4W_5W);
            Assert.assertEquals("Alzarri Joseph", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopStrikeRateWithBestBowlingAverage() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.BOWLING_AVG_SR);
            Assert.assertEquals("Krishnappa Gowtham", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forMostWicketWithBestBowlingAverage() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.WICKET);
            Assert.assertEquals("Suresh Raina", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_BestBowlingAndBattingAverage() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.BATTING_BOWLING_AVG);
            String battingAndBowlingAverage = premierLeague.bestBattingAndBowlingAverage(average);
            Assert.assertEquals("Krishnappa Gowtham", battingAndBowlingAverage);
        } catch (IplAnalyserException e) {
            e.printStackTrace();//    @Test
//    public void givenIplDataFile_MostRunsAndWicket() {
//        try {
//            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH,IPL_CSV_BOWLING_FILE_PATH);
//            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.RUNS);
//            String battingAndBowlingAverage = premierLeague.mostRunAndMostWicket(average);
//            Assert.assertEquals("Kagiso Rabada", battingAndBowlingAverage);
//        } catch (IplAnalyserException e) {
//            e.printStackTrace();
//        }
        }
    }

    @Test
    public void givenIplDataFile_MostRunsAndWicket() {
        try {
            Map<String, IplDAO> loadIplData = premierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN,IPL_CSV_FILE_PATH,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = premierLeague.sort(loadIplData, IplFields.RUNS);
            String battingAndBowlingAverage = premierLeague.mostRunAndMostWicket(average);
            Assert.assertEquals("Kagiso Rabada", battingAndBowlingAverage);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }
}

