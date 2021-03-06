package iplanalyser;

public class IplDAO {
    String player;
    int match;
    int innings;
    int runs;
    double bowlingAverage;
    double battingAverage;
    double sr;
    int four;
    int six;
    int wicket;
    double over;
    double economy;
    int fourWkt;
    int fiveWkt;

    public IplDAO() {
    }

    public IplDAO(String player, int match, int innings, int runs, double bowlingAverage, double battingAverage, double sr, int four, int six, int wicket, double over, double economy, int fourWkt, int fiveWkt) {
        this.player = player;
        this.match = match;
        this.innings = innings;
        this.runs = runs;
        this.bowlingAverage = bowlingAverage;
        this.battingAverage = battingAverage;
        this.sr = sr;
        this.four = four;
        this.six = six;
        this.wicket = wicket;
        this.over = over;
        this.economy = economy;
        this.fourWkt = fourWkt;
        this.fiveWkt = fiveWkt;
    }

    public IplDAO(IplMostRunDTO censusCSV) {
        player = censusCSV.player;
        battingAverage = censusCSV.average.contains("-")?0:Double.parseDouble(censusCSV.average);
        innings = censusCSV.innings;
        match = censusCSV.match;
        runs = censusCSV.runs;
        sr = censusCSV.sr;
        four = censusCSV.four;
        six = censusCSV.six;
    }

    public IplDAO(IplMostWicketDTO iplMostWicketDTO) {
        player = iplMostWicketDTO.player;
        bowlingAverage = iplMostWicketDTO.average.contains("-")?0:Double.parseDouble(iplMostWicketDTO.average);
        innings = iplMostWicketDTO.innings.contains("-")?0:Integer.parseInt(iplMostWicketDTO.innings);
        match = iplMostWicketDTO.match.contains("-")?0:Integer.parseInt(iplMostWicketDTO.wicket);
        runs = iplMostWicketDTO.runs.contains("-")?0:Integer.parseInt(iplMostWicketDTO.runs);
        sr = iplMostWicketDTO.sr.contains("-")?0:Double.parseDouble(iplMostWicketDTO.sr);
        over = iplMostWicketDTO.ov.contains("-")?0:Double.parseDouble(iplMostWicketDTO.ov);
        wicket = iplMostWicketDTO.wicket.contains("-")?0:Integer.parseInt(iplMostWicketDTO.wicket);
        economy = iplMostWicketDTO.economy.contains("-")?0:Double.parseDouble(iplMostWicketDTO.economy);
        fourWkt = iplMostWicketDTO.fourWkt.contains("-")?0:Integer.parseInt(iplMostWicketDTO.fourWkt);
        fiveWkt = iplMostWicketDTO.fiveWkt.contains("-")?0:Integer.parseInt(iplMostWicketDTO.fiveWkt);
    }
}
