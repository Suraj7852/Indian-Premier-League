package iplanalyser;

import java.util.Map;

public class BowlingAdapter extends IplAdapter{
    @Override
    public <E> Map<String, IplDAO> loadIplData(IndianPremierLeague.Cricket cricket, String... filepath) throws IplAnalyserException {
        Map<String, IplDAO> loadIplData = super.loadIplData(IplMostWicketDTO.class, filepath);
        return loadIplData;
    }
}
