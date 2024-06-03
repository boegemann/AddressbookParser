package org.addressbook.ingo.analyser.generic;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Data Analyser implementation for Iterables
 */
public class IterableDataAnalysisRunner {

    /**
     * IterableDataAnalysisRunner
     * Generic Runner Implementation to parse an iterable datasource of type T
     * into a defined format B and analyse it with a collection of Streaming Analysers
     * returning a Map with the result keyed by the name in the analyser map representing the analyser
     * @param source The iterable to be analyser
     * @param parser The parser implementation to read the row into a bean/record format
     * @param analyserMap Mao object containing analyser implementations keyed by result names
     * @return Map constaining the results keyed by result names
     * @param <T> Input type
     * @param <B> Outbut Bean type
     */
    public static<T,B> Map<String, String> analyse(
            Iterable<T> source,
            RowParser<T, B> parser,
            Map<String, StreamingAnalyser<B>> analyserMap) {
        for (T row : source) {
            B parsed = parser.parseRow(row);
            analyserMap.values().forEach(analyser ->
                    analyser.digest(parsed)
            );
        }
        return analyserMap.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().getResult()
        ));
    }
}
