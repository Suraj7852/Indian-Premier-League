package iplanalyser;

import com.google.gson.Gson;

import java.util.*;
import java.util.stream.Collectors;

public class IndianPremierLeague {

    public enum Cricket {BATSMAN, BOWLER}

    ;
    Map<IplFields, Comparator<IplDAO>> iplField = null;

    public IndianPremierLeague() {
        this.iplField = new HashMap<>();
        this.iplField.put(IplFields.AVERAGE, Comparator.comparing(iplDAO -> iplDAO.battingAverage, Comparator.reverseOrder()));
        Comparator<IplDAO> strikeRate = Comparator.comparing(iplDAO -> iplDAO.sr, Comparator.reverseOrder());
        this.iplField.put(IplFields.STRIKE_RATE, strikeRate);
        this.iplField.put(IplFields.SIX, Comparator.comparing(iplDAO -> iplDAO.six, Comparator.reverseOrder()));
        Comparator<IplDAO> averageStrikeRate = Comparator.comparing(iplDAO -> iplDAO.battingAverage);
        this.iplField.put(IplFields.AVG_WITH_SR, averageStrikeRate.thenComparing(ipl -> ipl.sr).reversed());
        Comparator<IplDAO> runsAverage = Comparator.comparing(iplDAO -> iplDAO.runs);
        this.iplField.put(IplFields.RUN_WITH_AVG, runsAverage.thenComparing(ipl -> ipl.bowlingAverage).reversed());
        this.iplField.put(IplFields.ECONOMY, Comparator.comparing(iplDAO -> iplDAO.economy));
        Comparator<IplDAO> fiveWkt = Comparator.comparing(iplDTO -> iplDTO.fiveWkt, Comparator.reverseOrder());
        this.iplField.put(IplFields.SR_WITH_4W_5W, fiveWkt.thenComparing(iplDAO -> iplDAO.fourWkt, Comparator.reverseOrder())
                .thenComparing(iplDAO -> iplDAO.sr, Comparator.reverseOrder()));
        this.iplField.put(IplFields.BOWLING_AVG_SR, averageStrikeRate.thenComparing(iplDAO -> iplDAO.sr).reversed());
        Comparator<IplDAO> wicket = Comparator.comparing(iplDTO -> iplDTO.wicket, Comparator.reverseOrder());
        this.iplField.put(IplFields.WICKET, wicket.thenComparing(iplDAO -> iplDAO.bowlingAverage).reversed());
        Comparator<IplDAO> battingAverage = Comparator.comparing(iplDTO -> iplDTO.battingAverage);
        this.iplField.put(IplFields.BATTING_BOWLING_AVG, battingAverage.thenComparing(iplDAO -> iplDAO.bowlingAverage, Comparator.reverseOrder()));
        this.iplField.put(IplFields.RUNS,Comparator.comparing(iplDAO -> iplDAO.runs,Comparator.reverseOrder()));
    }

    public Map<String, IplDAO> loadIplData(Cricket cricket, String... filePath) throws IplAnalyserException {
        IplAdapter iplAdapter = IplAnalyserFactory.loadIplData(cricket);
        return iplAdapter.loadIplData(cricket, filePath);
    }

    public IplDAO[] sort(Map<String, IplDAO> loadIplData, IplFields fields) {
        ArrayList ipl = loadIplData.values().stream()
                .sorted(this.iplField.get(fields))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedIplList = new Gson().toJson(ipl);
        IplDAO[] iplDAOS = new Gson().fromJson(sortedIplList, IplDAO[].class);
        return iplDAOS;
    }

    public String maxFourSix(Map<String, IplDAO> daoMap, IplFields six) {
        IplDAO[] sort = this.sort(daoMap, six);
        int valueOfSixFOur = 0;
        int max = 0;
        String playerName = "";
        for (int i = 0; i < sort.length; i++) {
            valueOfSixFOur = sort[i].six * 6 + sort[i].four * 4;
            if (valueOfSixFOur >= max) {
                max = valueOfSixFOur;
                playerName = sort[i].player;
            }
        }
        return playerName;
    }

    public String strikeRateOfSixAndFour(Map<String, IplDAO> iplDAOMap, IplFields six) {
        IplDAO[] sort = this.sort(iplDAOMap, six);
        double scoreBasedOnSixFOur = 0;
        double noOfBallPlayed = 0;
        double max = 0;
        double strikeRate = 0.0;
        String playerName = "";
        for (int i = 0; i < sort.length; i++) {
            scoreBasedOnSixFOur = sort[i].six * 6 + sort[i].four * 4;
            noOfBallPlayed = sort[i].six + sort[i].four;
            strikeRate = (scoreBasedOnSixFOur / noOfBallPlayed) * 100;
            if (strikeRate >= max) {
                max = strikeRate;
                playerName = sort[i].player;
            }
        }
        return playerName;
    }

    public IplDAO[] greatAverageWithGreatStrikeRate(Map<String, IplDAO> iplDAOMap, IplFields average) {
        return this.sort(iplDAOMap, average);
    }

    public String bestBattingAndBowlingAverage(IplDAO[] average) {
        double averageForBatBall;
        double max = 0;
        String player = "";
        for (int i = 0; i < average.length; i++) {
            averageForBatBall = (average[i].battingAverage + average[i].bowlingAverage) / 2;
            if (averageForBatBall > max) {
                max = averageForBatBall;
                player = average[i].player;
            }
        }
        return player;
    }

    public String mostRunAndMostWicket(IplDAO[] average) {
        double max = 0;
        String player = "";
        for (int i = 0; i < average.length; i++) {
            if (average[i].wicket > max) {
                max = average[i].wicket;
                player = average[i].player;
            }
        }
        return player;
    }
}