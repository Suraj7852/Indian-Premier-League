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

public abstract class IplAdapter {
    public abstract <E> Map<String, IplDAO> loadIplData(IndianPremierLeague.Cricket cricket, String... filepath) throws IplAnalyserException;

    public <E> Map<String, IplDAO> loadIplData(Class<E> censusCSVCLass, String... filePath) throws IplAnalyserException {
        Map<String, IplDAO> iplPlayerMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath[0]))
        ) {

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplMostRunDTO> csvFileIterator = csvBuilder.getCSVFileIterator(reader, IplMostRunDTO.class);
            Iterable<IplMostRunDTO> csvIterable = () -> csvFileIterator;
            StreamSupport.stream(csvIterable.spliterator(), false)
                    .map(IplMostRunDTO.class::cast)
                    .forEach(iplDAO -> iplPlayerMap.put(iplDAO.player, new IplDAO(iplDAO)));
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
