package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplDTO {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public String match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "SR", required = true)
    public String sr;

    public IplDTO(IplDTO censusCSV) {
    this.player=censusCSV.player;
    this.average= censusCSV.average;
    this.innings=censusCSV.innings;
    this.match=censusCSV.match;
    this.runs=censusCSV.runs;
    this.sr=censusCSV.sr;
    }

    public IplDTO() {
    }
}
