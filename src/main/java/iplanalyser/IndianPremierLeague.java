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
        this.iplField.put(IplFields.STRIKE_RATE,Comparator.comparing(iplDAO -> iplDAO.sr,Comparator.reverseOrder()));
    }

    public Map<String, IplDAO> loadIplData(String filePath) throws IplAnalyserException {
        Map<String, IplDAO> iplPlayerMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))
        ){

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplDTO> csvFileIterator = csvBuilder.getCSVFileIterator(reader,IplDTO.class);
            Iterable<IplDTO> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(),false)
                        .map(IplDTO.class::cast)
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

    public String sort(Map<String, IplDAO> loadIplData,IplFields fields) {
        ArrayList ipl = loadIplData.values().stream()
                .sorted(this.iplField.get(fields))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedIplList = new Gson().toJson(ipl);
        return sortedIplList;
    }
}
