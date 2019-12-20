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
        innings = iplMostWicketDTO.innings;
        match = iplMostWicketDTO.match;
        runs = iplMostWicketDTO.runs;
        sr = iplMostWicketDTO.sr;
        over = iplMostWicketDTO.ov;
        wicket = iplMostWicketDTO.wicket;
    }

}
