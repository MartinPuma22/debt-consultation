package com.demo.poc.commons.repository.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class CsvFileReader {

  public static List<CSVRecord> getRecords(String filePath, char delimiter) {
    try {
      InputStream inputStream = CsvFileReader.class.getResourceAsStream(filePath);
      InputStreamReader reader = new InputStreamReader(inputStream);
      CSVFormat csvFormat = CSVFormat.newFormat(delimiter).withHeader().withTrim();
      CSVParser csvParser = new CSVParser(reader, csvFormat);
      return csvParser.getRecords();
    } catch (Exception exception) {
      throw new RuntimeException("Error reading CSV: " + exception.getMessage(), exception);
    }
  }

}
