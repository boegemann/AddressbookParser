package org.addressbook.ingo.analyser;

import org.addressbook.ingo.analyser.addresscsv.CsvReader;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SpecifiedScenarioRunnerTest {

    @Test
    public void givenAFileResourceWithCSVRecordsICanGetAnIterable() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("AddressBook.csv"))
        ))) {
            Iterable<CSVRecord> csvIterable = new CsvReader().getCsvIterable(reader);
            assertNotNull(csvIterable);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void iGivenTheProvidedAddressBookFileIGetTheSpecifiedOutputs() {
        Map<String, String> results = new SpecifiedScenarioRunner().runSpecifiedScenario();
        assertTrue(results.containsKey("Males"));
        assertTrue(results.containsKey("Oldest Person"));
        assertTrue(results.containsKey("Age Difference Bill and Paul"));
    }

}