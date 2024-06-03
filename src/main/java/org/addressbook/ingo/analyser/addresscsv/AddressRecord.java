package org.addressbook.ingo.analyser.addresscsv;

import java.time.LocalDate;

public record AddressRecord(String name, String gender, LocalDate dob) {
}
