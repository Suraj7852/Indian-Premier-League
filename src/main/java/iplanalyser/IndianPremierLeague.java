package iplanalyser;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class IndianPremierLeague {
    Map<IplFields,Comparator<IplDAO>> iplField = null;
    public IndianPremierLeague() {
        this.iplField = new HashMap<>();
        this.iplField.put(IplFields.AVERAGE,Comparator.comparing(iplDAO -> iplDAO.average,Comparator.reverseOrder()));
        Comparator<IplDAO> strikeRate = Comparator.comparing(iplDAO -> iplDAO.sr, Comparator.reverseOrder());
        this.iplField.put(IplFields.STRIKE_RATE,strikeRate);
        this.iplField.put(IplFields.SIX,Comparator.comparing(iplDAO -> iplDAO.six,Comparator.reverseOrder()));
        Comparator<IplDAO>averageStrikeRate = Comparator.comparing(iplDAO -> iplDAO.average);
        this.iplField.put(IplFields.AVG_WITH_SR,averageStrikeRate.thenComparing(ipl -> ipl.sr).reversed());
        Comparator<IplDAO>runsAverage = Comparator.comparing(iplDAO -> iplDAO.runs);
        this.iplField.put(IplFields.RUN_WITH_AVG,runsAverage.thenComparing(ipl -> ipl.average).reversed());
        this.iplField.put(IplFields.ECONOMY,Comparator.comparing(iplDAO -> iplDAO.economy));
        Comparator<IplDAO>fiveWkt = Comparator.comparing(iplDTO -> iplDTO.fiveWkt,Comparator.reverseOrder());
        this.iplField.put(IplFields.SR_WITH_4W_5W,fiveWkt.thenComparing(iplDAO -> iplDAO.fourWkt,Comparator.reverseOrder())
                    .thenComparing(iplDAO -> iplDAO.sr,Comparator.reverseOrder()));
        this.iplField.put(IplFields.BOWLING_AVG_SR,averageStrikeRate.thenComparing(iplDAO -> iplDAO.sr).reversed());
    }

    public Map<String, IplDAO> loadIplData(String filePath) throws IplAnalyserException {
        Map<String, IplDAO> iplPlayerMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))
        ){

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplMostRunDTO> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplMostRunDTO.class);
            Iterable<IplMostRunDTO> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(),false)
                        .map(IplMostRunDTO.class::cast)
                        .forEach(iplDAO -> iplPlayerMap.put(iplDAO.player,new IplDAO(iplDAO)));
            return iplPlayerMap;
        } catch (IOException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.HEADER_MISMATCH);
        }
    }

    public Map<String, IplDAO> loadIplWicketData(String filePath) throws IplAnalyserException {
        Map<String, IplDAO> iplPlayerMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))
        ){

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplMostWicketDTO> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplMostWicketDTO.class);
            Iterable<IplMostWicketDTO> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .map(IplMostWicketDTO.class::cast)
                    .forEach(iplDAO -> iplPlayerMap.put(iplDAO.player,new IplDAO(iplDAO)));
            return iplPlayerMap;
        } catch (IOException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.HEADER_MISMATCH);
        }
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
        int valueOfSixFOur=0;
        int max = 0;
        String playerName = "";
        for (int i=0;i<sort.length;i++) {
            valueOfSixFOur = sort[i].six*6 + sort[i].four*4;
            if (valueOfSixFOur >= max){
                max = valueOfSixFOur;
                playerName = sort[i].player;
            }
        }
        return playerName;
    }

    public String strikeRateOfSixAndFour(Map<String, IplDAO> iplDAOMap, IplFields six) {
        IplDAO[] sort = this.sort(iplDAOMap, six);
        double scoreBasedOnSixFOur=0;
        double noOfBallPlayed = 0;
        double max = 0;
        double strikeRate = 0.0;
        String playerName = "";
        for (int i=0;i<sort.length;i++) {
            scoreBasedOnSixFOur = sort[i].six*6 + sort[i].four*4;
            noOfBallPlayed = sort[i].six + sort[i].four;
            strikeRate = (scoreBasedOnSixFOur/noOfBallPlayed)*100;
            if (strikeRate >= max){
                max = strikeRate;
                playerName = sort[i].player;
            }
        }
        return playerName;
    }

    public IplDAO[] greatAverageWithGreatStrikeRate(Map<String, IplDAO> iplDAOMap, IplFields average) {
        return this.sort(iplDAOMap, average);
    }
}