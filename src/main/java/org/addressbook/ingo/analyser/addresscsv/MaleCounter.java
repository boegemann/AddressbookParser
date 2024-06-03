package org.addressbook.ingo.analyser.addresscsv;

import org.addressbook.ingo.analyser.generic.StreamingAnalyser;

public class MaleCounter implements StreamingAnalyser<AddressRecord> {
    private int countMen;

    @Override
    public void digest(AddressRecord bean) {
        if (bean.gender().trim().equalsIgnoreCase("male")) countMen++;
    }

    @Override
    public String getResult() {
        return String.valueOf(countMen);
    }
}
