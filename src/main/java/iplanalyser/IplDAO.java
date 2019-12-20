package iplanalyser;

public class IplDAO {
    String player;
    int match;
    int innings;
    int runs;
    double average;
    double sr;
    int four;
    int six;
    int wicket;
    double over;
    double economy;

    public IplDAO() {
    }

    public IplDAO(IplMostRunDTO censusCSV) {
        player = censusCSV.player;
        average = censusCSV.average.contains("-")?0:Double.parseDouble(censusCSV.average);
        innings = censusCSV.innings;
        match = censusCSV.match;
        runs = censusCSV.runs;
        sr = censusCSV.sr;
        four = censusCSV.four;
        six = censusCSV.six;
    }

    public IplDAO(IplMostWicketDTO iplMostWicketDTO) {
        player = iplMostWicketDTO.player;
        average = iplMostWicketDTO.average.contains("-")?0:Double.parseDouble(iplMostWicketDTO.average);
        innings = iplMostWicketDTO.innings.contains("-")?0:Integer.parseInt(iplMostWicketDTO.innings);
        match = iplMostWicketDTO.match.contains("-")?0:Integer.parseInt(iplMostWicketDTO.wicket);
        runs = iplMostWicketDTO.runs.contains("-")?0:Integer.parseInt(iplMostWicketDTO.runs);
        sr = iplMostWicketDTO.sr.contains("-")?0:Double.parseDouble(iplMostWicketDTO.sr);
        over = iplMostWicketDTO.ov.contains("-")?0:Double.parseDouble(iplMostWicketDTO.ov);
        wicket = iplMostWicketDTO.wicket.contains("-")?0:Integer.parseInt(iplMostWicketDTO.wicket);
        economy = iplMostWicketDTO.economy.contains("-")?0:Double.parseDouble(iplMostWicketDTO.economy);
    }

}
