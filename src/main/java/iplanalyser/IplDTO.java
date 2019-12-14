package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplDTO {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public int match;

    @CsvBindByName(column = "Inns", required = true)
    public int innings;

    @CsvBindByName(column = "Runs", required = true)
    public int runs;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "SR", required = true)
    public double sr;

    public IplDTO(String player, int match, int innings, int runs, String average, double sr) {
        this.player = player;
        this.match = match;
        this.innings = innings;
        this.runs = runs;
        this.average = average;
        this.sr = sr;
    }

    public IplDTO() {
    }
}
