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
    public Map<String, IplDTO> loadIplData(String filePath) throws IplAnalyserException {
        Map<String, IplDTO> iplDTOMap = new HashMap<>();
        try (Reader reader = Files.newBufferedReader(Paths.get(filePath))
        ){

            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<IplDTO> csvFileIterator = csvBuilder.getCSVFileIterator(reader,IplDTO.class);
            Iterable<IplDTO> csvIterable = () -> csvFileIterator;
                StreamSupport.stream(csvIterable.spliterator(),false).
                        forEach(iplDTO -> iplDTOMap.put(iplDTO.player,new IplDTO(iplDTO)));
            return iplDTOMap;
        } catch (IOException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException e) {
            throw new IplAnalyserException(e.getMessage(), IplAnalyserException.ExceptionType.HEADER_MISMATCH);
        }
    }

    public String sortByAverage(Map<String, IplDTO> loadIplData) {
        Comparator<IplDTO> comparing = Comparator.comparing(iplDTO -> iplDTO.average,Comparator.reverseOrder());
        ArrayList ipl = loadIplData.values().stream()
                .sorted(comparing)
                .filter(x -> !x.average.equals("-"))
                .collect(Collectors.toCollection(ArrayList::new));
        String sortedIplList = new Gson().toJson(ipl);
        return sortedIplList;
    }
}
