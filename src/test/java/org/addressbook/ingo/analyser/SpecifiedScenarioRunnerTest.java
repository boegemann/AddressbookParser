package org.addressbook.ingo.analyser;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SpecifiedScenarioRunnerTest {

    @Test
    public void iGivenTheProvidedAddressBookFileIGetTheSpecifiedOutputs(){
        Map<String,String> results = new SpecifiedScenarioRunner().runSpecifiedScenario();
        assertTrue(results.containsKey("Males"));
        assertTrue(results.containsKey("Oldest Person"));
        assertTrue(results.containsKey("Age Difference Bill and Paul"));
    }

}