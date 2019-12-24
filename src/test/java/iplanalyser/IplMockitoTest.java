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
            IplAdapter iplAdapter = mock(IplAdapter.class);
            when(iplAdapter.loadIplData(IplMostRunDTO.class, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            Map<String, IplDAO> realMap = iplAdapter.loadIplData(IplMostRunDTO.class, IPL_CSV_FILE_PATH);
            Assert.assertEquals(2, realMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenSampleData_WhenBowlingAdapterMock_ShouldReturnMap() {
        try {
            BowlingAdapter bowlingAdapter = mock(BowlingAdapter.class);
            when(bowlingAdapter.loadIplData(IplMostWicketDTO.class, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            Map<String, IplDAO> iplDAOMap = bowlingAdapter.loadIplData(IplMostWicketDTO.class, IPL_CSV_FILE_PATH);
            Assert.assertEquals(2, iplDAOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenSampleData_WhenBattingBowlingAdapterMock_ShouldReturnMap() {
        try {
            BattingBowlingAdapter battingBowlingAdapter = mock(BattingBowlingAdapter.class);
            when(battingBowlingAdapter.loadIplData(IplMostWicketDTO.class, IPL_CSV_FILE_PATH)).thenReturn(daoMap);
            Map<String, IplDAO> iplDAOMap = battingBowlingAdapter.loadIplData(IplMostWicketDTO.class, IPL_CSV_FILE_PATH);
            Assert.assertEquals(2, iplDAOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }
}
