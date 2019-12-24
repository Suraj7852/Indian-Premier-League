package iplanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class BowlingAdapterTest {private static final String IPL_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    private static final String IPL_CSV_BOWLING_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostWkts.csv";

    @Test
    public void given_iplData_ShouldReturnCount() {
        BowlingAdapter bowlingAdapter = new BowlingAdapter();
        try {
            Map<String, IplDAO> stringIplDAOMap = bowlingAdapter.loadIplData(IplMostWicketDTO.class, IPL_CSV_BOWLING_FILE_PATH);
            Assert.assertEquals(97,stringIplDAOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void given_iplData_IfNullPathShouldReturnException() {
        BowlingAdapter bowlingAdapter = new BowlingAdapter();
        try {
            Map<String, IplDAO> stringIplDAOMap = bowlingAdapter.loadIplData(IplMostWicketDTO.class, null);
        } catch (IplAnalyserException e) {
            Assert.assertEquals(IplAnalyserException.ExceptionType.HEADER_MISMATCH,e.type);
        }
    }
}
