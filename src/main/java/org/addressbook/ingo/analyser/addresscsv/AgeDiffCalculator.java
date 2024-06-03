package org.addressbook.ingo.analyser.addresscsv;

import org.addressbook.ingo.analyser.generic.StreamingAnalyser;

import java.time.Duration;
import java.time.LocalDate;

public class AgeDiffCalculator implements StreamingAnalyser<AddressRecord> {
    private final String firstPerson;
    private final String secondPerson;

    private LocalDate firstDOB = null;
    private LocalDate secondDOB = null;

    public AgeDiffCalculator(String firstPerson, String secondPerson){
        this.firstPerson = firstPerson.trim();
        this.secondPerson = secondPerson.trim();
    }

    @Override
    public void digest(AddressRecord bean) {
        if (bean.name().trim().equalsIgnoreCase(firstPerson)){
            firstDOB = bean.dob();
        }
        if (bean.name().trim().equalsIgnoreCase(secondPerson)){
            secondDOB = bean.dob();
        }
    }

    @Override
    public String getResult() {
        if (firstDOB==null || secondDOB==null) return ("Not all persons found");
        return String.valueOf(Duration.between(firstDOB.atStartOfDay(),secondDOB.atStartOfDay()).toDays());
    }
}