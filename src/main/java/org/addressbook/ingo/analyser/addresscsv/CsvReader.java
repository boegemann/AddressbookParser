package org.addressbook.ingo.analyser.addresscsv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;

/**
 * Util class to wrap the reading and generation of an iterable for the Apache commons CSV library
 */
public class CsvReader {
    public Iterable<CSVRecord> getCsvIterable(Reader streamReader) throws IOException {
        return CSVFormat.DEFAULT.builder()
                .build()
                .parse(streamReader) ;
    }
}
