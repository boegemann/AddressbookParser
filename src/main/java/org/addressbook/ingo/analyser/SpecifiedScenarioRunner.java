package org.addressbook.ingo.analyser;

import org.addressbook.ingo.analyser.addresscsv.*;
import org.addressbook.ingo.analyser.generic.IterableDataAnalysisRunner;
import org.addressbook.ingo.analyser.generic.StreamingAnalyser;
import org.apache.commons.csv.CSVRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SpecifiedScenarioRunner {

    public static void main(String[] args) {
        Map<String,String> results = new SpecifiedScenarioRunner().runSpecifiedScenario();
        results.forEach((key,value) -> System.out.println(key+": "+value));
    }

    public Map<String,String>  runSpecifiedScenario(){

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("AddressBook.csv"))
        ))) {
            Iterable<CSVRecord> csvIterable = new CsvReader().getCsvIterable(reader);

            AddressRecordParser parser = new AddressRecordParser();
            Map<String, StreamingAnalyser<AddressRecord>> analysers = new HashMap<>();
            analysers.put("Males" ,new MaleCounter());
            analysers.put("Oldest Person" ,new FindOldest());
            analysers.put("Age Difference Bill and Paul" ,new AgeDiffCalculator("Bill McKnight","Paul Robinson"));
            return IterableDataAnalysisRunner.analyse(csvIterable,parser,analysers);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
