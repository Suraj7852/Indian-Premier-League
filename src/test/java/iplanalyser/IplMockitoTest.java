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
        daoMap.put("Suraj", new IplDAO("Suraj", 1, 1, 1));
        daoMap.put("Laxman", new IplDAO("Laxman", 1, 1, 1));
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
}
