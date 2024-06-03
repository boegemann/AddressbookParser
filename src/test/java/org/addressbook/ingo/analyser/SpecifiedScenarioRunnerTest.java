package org.addressbook.ingo.analyser;

import org.addressbook.ingo.analyser.addresscsv.*;
import org.addressbook.ingo.analyser.generic.IterableDataAnalysisRunner;
import org.addressbook.ingo.analyser.generic.StreamingAnalyser;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    public void iCanParseTheRecordsInOurCSV() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("AddressBook.csv"))
        ))) {
            Iterable<CSVRecord> csvIterable = new CsvReader().getCsvIterable(reader);

            AddressRecordParser parser = new AddressRecordParser();
            List<AddressRecord> addresses = new ArrayList<>();
            csvIterable.forEach(record -> {
                addresses.add(parser.parseRow(record));

            });
            assertEquals(5,addresses.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testMalesCounter(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("AddressBook.csv"))
        ))) {
            Iterable<CSVRecord> csvIterable = new CsvReader().getCsvIterable(reader);

            AddressRecordParser parser = new AddressRecordParser();
            Map<String, StreamingAnalyser<AddressRecord>> analysers = new HashMap<>();
            analysers.put("Males" ,new MaleCounter());
            Map<String, String> results = IterableDataAnalysisRunner.analyse(csvIterable,parser,analysers);
            assertEquals(1,results.size());
            assertEquals("3",results.get("Males"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testOldestCounter(){
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("AddressBook.csv"))
        ))) {
            Iterable<CSVRecord> csvIterable = new CsvReader().getCsvIterable(reader);

            AddressRecordParser parser = new AddressRecordParser();
            Map<String, StreamingAnalyser<AddressRecord>> analysers = new HashMap<>();
            analysers.put("Oldest Person" ,new FindOldest());
            Map<String, String> results = IterableDataAnalysisRunner.analyse(csvIterable,parser,analysers);
            assertEquals(1,results.size());
            assertEquals("Wes Jackson",results.get("Oldest Person"));
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