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
}
