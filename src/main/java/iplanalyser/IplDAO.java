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

    public IplDAO() {
    }

    public IplDAO(IplDTO censusCSV) {
        player=censusCSV.player;
        if (censusCSV.average.contains("-"))
            average = 0;
        else
            average= Double.parseDouble(censusCSV.average);
        innings=censusCSV.innings;
        match=censusCSV.match;
        runs=censusCSV.runs;
        sr=censusCSV.sr;
        four = censusCSV.four;
        six = censusCSV.six;
    }

}
