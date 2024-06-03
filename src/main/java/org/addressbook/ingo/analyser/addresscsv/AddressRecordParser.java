package org.addressbook.ingo.analyser.addresscsv;

import org.addressbook.ingo.analyser.generic.RowParser;
import org.apache.commons.csv.CSVRecord;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AddressRecordParser implements RowParser<CSVRecord, AddressRecord> {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    @Override
    public AddressRecord parseRow(CSVRecord row) {
        String name = row.get(0).trim();
        String gender = row.get(1).trim();
        String dobString = row.get(2).trim();
        LocalDate dob = LocalDate.parse(dobString.trim(),formatter);
        return new AddressRecord(name,gender,dob);
    }
}
