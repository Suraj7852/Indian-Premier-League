package iplanalyser;

import com.opencsv.bean.CsvBindByName;

public class IplMostWicketDTO {
    @CsvBindByName(column = "PLAYER", required = true)
    public String player;

    @CsvBindByName(column = "Mat", required = true)
    public String match;

    @CsvBindByName(column = "Inns", required = true)
    public String innings;

    @CsvBindByName(column = "Runs", required = true)
    public String runs;

    @CsvBindByName(column = "Avg", required = true)
    public String average;

    @CsvBindByName(column = "SR", required = true)
    public String sr;

    @CsvBindByName(column = "Ov", required = true)
    public String ov;

    @CsvBindByName(column = "Wkts", required = true)
    public String wicket;

    @CsvBindByName(column = "Econ", required = true)
    public String economy;

    @CsvBindByName(column = "4w", required = true)
    public String fourWkt;

    @CsvBindByName(column = "5w", required = true)
    public String fiveWkt;

    public IplMostWicketDTO(String player, String match, String innings, String runs, String average, String sr, String ov, String wicket, String economy, String fourWkt, String fiveWkt) {
        this.player = player;
        this.match = match;
        this.innings = innings;
        this.runs = runs;
        this.average = average;
        this.sr = sr;
        this.ov = ov;
        this.wicket = wicket;
        this.economy = economy;
        this.fourWkt = fourWkt;
        this.fiveWkt = fiveWkt;
    }

    public IplMostWicketDTO() {
    }
}
