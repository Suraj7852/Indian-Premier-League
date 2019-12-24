package iplanalyser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IplMockitoTest {
    private static final String IPL_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_BOWLING_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";
    @Mock
    IplAdapter iplAdapter;
    BowlingAdapter bowlingAdapter;
    BattingBowlingAdapter battingBowlingAdapter;
    Map<String, IplDAO> daoMap = null;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void setUp() {
        daoMap = new HashMap<>();
        daoMap.put("Suraj", new IplDAO("suraj",2,3,663,54,54,5,6,5,58,9,96,6,6));
        daoMap.put("Laxman", new IplDAO("laxmun",3,5,625,6,9,78,5,5,2,36,6,6,6));
    }

    @Test
    public void givenSampleData_ShouldReturnCount() {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BATSMAN).getClass());
            when(iplAdapter.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(iplAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH);
            Assert.assertEquals(2, iplDAOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenSampleData_WhenBowlingAdapterMock_ShouldReturnMap() {
        try {
            IplAdapter bowlingAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BOWLER).getClass());
            when(bowlingAdapter.loadIplData(IndianPremierLeague.Cricket.BOWLER, IPL_CSV_BOWLING_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(bowlingAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            Assert.assertEquals(2, iplDAOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenSampleData_WhenBattingBowlingAdapterMock_ShouldReturnMap() {
        try {
            IplAdapter battingBowlingAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BOWLER).getClass());
            when(battingBowlingAdapter.loadIplData(IndianPremierLeague.Cricket.BOWLER, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(battingBowlingAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER, IPL_CSV_FILE_PATH);
            Assert.assertEquals(2, iplDAOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopBattingAverage() {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BATSMAN).getClass());
            when(iplAdapter.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(iplAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH);
            IplDAO[] average = indianPremierLeague.sort(iplDAOMap, IplFields.AVERAGE);
            Assert.assertEquals("suraj",average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopStrikeRate() {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BATSMAN).getClass());
            when(iplAdapter.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(iplAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH);
            IplDAO[] average = indianPremierLeague.sort(iplDAOMap, IplFields.STRIKE_RATE);
            Assert.assertEquals("laxmun",average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_WhoHitMat_SixAndFour() {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BATSMAN).getClass());
            when(iplAdapter.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(iplAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH);
            IplDAO[] average = indianPremierLeague.sort(iplDAOMap, IplFields.SIX);
            Assert.assertEquals("suraj",average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_strikeRateBasedOnFourAndSix() {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BATSMAN).getClass());
            when(iplAdapter.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(iplAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH);
            String average = indianPremierLeague.strikeRateOfSixAndFour(iplDAOMap, IplFields.SIX);
            Assert.assertEquals("laxmun",average);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_greatAverageWith_greatStrikingRate() {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BATSMAN).getClass());
            when(iplAdapter.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(iplAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH);
            IplDAO[] average = indianPremierLeague.greatAverageWithGreatStrikeRate(iplDAOMap, IplFields.AVG_WITH_SR);
            Assert.assertEquals("suraj",average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_maximumRunWith_greatAverage() {
        try {
            IplAdapter iplAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BATSMAN).getClass());
            when(iplAdapter.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(iplAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BATSMAN, IPL_CSV_FILE_PATH);
            IplDAO[] average = indianPremierLeague.greatAverageWithGreatStrikeRate(iplDAOMap, IplFields.RUN_WITH_AVG);
            Assert.assertEquals("suraj",average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopBowlingStrikeRate() {
        try {
            IplAdapter bowlingAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BOWLER).getClass());
            when(bowlingAdapter.loadIplData(IndianPremierLeague.Cricket.BOWLER, IPL_CSV_BOWLING_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(bowlingAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = indianPremierLeague.sort(iplDAOMap, IplFields.RUN_WITH_AVG);
            Assert.assertEquals("suraj", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopBowlingEconomyRate() {
        try {
            IplAdapter bowlingAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BOWLER).getClass());
            when(bowlingAdapter.loadIplData(IndianPremierLeague.Cricket.BOWLER, IPL_CSV_BOWLING_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(bowlingAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = indianPremierLeague.sort(iplDAOMap, IplFields.ECONOMY);
            Assert.assertEquals("laxmun", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forTopStrikeRateWith4WicketAnd5Wicket() {
        try {
            IplAdapter bowlingAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BOWLER).getClass());
            when(bowlingAdapter.loadIplData(IndianPremierLeague.Cricket.BOWLER, IPL_CSV_BOWLING_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(bowlingAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = indianPremierLeague.sort(iplDAOMap, IplFields.SR_WITH_4W_5W);
            Assert.assertEquals("laxmun", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenIplDataFile_forMostWicketWithBestBowlingAverage() {
        try {
            IplAdapter bowlingAdapter = mock(IplAnalyserFactory.loadIplData(IndianPremierLeague.Cricket.BOWLER).getClass());
            when(bowlingAdapter.loadIplData(IndianPremierLeague.Cricket.BOWLER, IPL_CSV_BOWLING_FILE_PATH)).thenReturn(daoMap);
            IndianPremierLeague indianPremierLeague = new IndianPremierLeague();
            indianPremierLeague.setIplAdapter(bowlingAdapter);
            Map<String, IplDAO> iplDAOMap = indianPremierLeague.loadIplData(IndianPremierLeague.Cricket.BOWLER,IPL_CSV_BOWLING_FILE_PATH);
            IplDAO[] average = indianPremierLeague.sort(iplDAOMap, IplFields.WICKET);
            Assert.assertEquals("laxmun", average[0].player);
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }
}
