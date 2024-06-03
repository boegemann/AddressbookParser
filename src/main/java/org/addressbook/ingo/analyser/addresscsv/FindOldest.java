package org.addressbook.ingo.analyser.addresscsv;

import org.addressbook.ingo.analyser.generic.StreamingAnalyser;

import java.time.LocalDate;

/**
 * StreamingAnalyser implementation to find the oldest person contained in the address book
 */
public class FindOldest implements StreamingAnalyser<AddressRecord> {
    private LocalDate oldest = null;
    private String name = "";

    @Override
    public void digest(AddressRecord bean) {
        if (oldest == null || bean.dob().isBefore(oldest)) {
            oldest = bean.dob();
            name = bean.name();
        }
    }

    @Override
    public String getResult() {
        return oldest == null ? "No one" : name;
    }
}