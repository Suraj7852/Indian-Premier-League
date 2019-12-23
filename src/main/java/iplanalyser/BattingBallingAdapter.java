package iplanalyser;

import CSVBuilder.CSVBuilderException;
import CSVBuilder.CSVBuilderFactory;
import CSVBuilder.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.StreamSupport;

public class BattingBallingAdapter extends IplAdapter {

    @Override
    public <E> Map<String, IplDAO> loadIplData(IndianPremierLeague.Cricket cricket, String... filepath) throws IplAnalyserException {
        Map<String, IplDAO> iplDAOMap = super.loadIplData(IplMostRunDTO.class, filepath[0]);
        if (filepath.length>1)
            this.loadIplWicketData(iplDAOMap,filepath[1]);
        return iplDAOMap;
    }

    private Map<String, IplDAO> loadIplWicketData(Map<String, IplDAO> iplDAOMap, String filePath) throws IplAnalyserException {
        Map<String, IplDAO> iplPlayerMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))
        ){

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplMostWicketDTO> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplMostWicketDTO.class);
            Iterable<IplMostWicketDTO> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(),false)
                    .map(IplMostWicketDTO.class::cast)
                    .forEach(iplDAO -> iplDAOMap.put(iplDAO.player,new IplDAO(iplDAO)));
            return iplPlayerMap;
        } catch (IOException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.HEADER_MISMATCH);
        }
    }
}
