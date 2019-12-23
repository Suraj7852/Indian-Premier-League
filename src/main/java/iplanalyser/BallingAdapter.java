package iplanalyser;

import java.util.Map;

public class BallingAdapter extends IplAdapter{
    @Override
    public <E> Map<String, IplDAO> loadIplData(IndianPremierLeague.Cricket cricket, String... filepath) throws IplAnalyserException {
        Map<String, IplDAO> loadIplData = super.loadIplData(IplMostWicketDTO.class, filepath);
        return loadIplData;
    }
}
