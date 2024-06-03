package org.addressbook.ingo.analyser.addresscsv;

import java.time.LocalDate;

/**
 * Record class representing individual rows in the addressbook.csv file
 * @param name
 * @param gender
 * @param dob
 */
public record AddressRecord(String name, String gender, LocalDate dob) {
}
