package org.addressbook.ingo.analyser.generic;

/**
 * A Row parser reads a given line of data of type T
 * and returns a bean/record with the data in the specified type of T
 *
 * @param <T> Input format (e.g. CSVRecord or similar)
 * @param <B> Output format (e.g. AddressbookRecord or similar)
 */
public interface RowParser<T, B> {
    B parseRow(T row);
}
