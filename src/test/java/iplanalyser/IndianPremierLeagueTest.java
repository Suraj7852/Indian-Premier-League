package iplanalyser;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class IndianPremierLeagueTest {
    private static final String IPL_CSV_FILE_PATH = "./src/test/resources/IPL2019FactsheetMostRuns.csv";
    
    @Test
    public void givenIplDataFile_ReturnsPlayerWith_BestBattingAvg() {
        IndianPremierLeague premierLeague = new IndianPremierLeague();
        try {
            Map<String, IplDTO> stringIplDTOMap = premierLeague.loadIplData(IPL_CSV_FILE_PATH);
            Assert.assertEquals(100,stringIplDTOMap.size());
        } catch (IplAnalyserException e) {
            e.printStackTrace();
        }
    }
}
