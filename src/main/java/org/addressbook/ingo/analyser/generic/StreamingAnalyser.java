package org.addressbook.ingo.analyser.generic;

/**
 * The streaming analyser is called for each line of a streamed data source
 * and calculates a result which can be extracted after the streaming has finished
 * @param <B>
 */
public interface StreamingAnalyser<B> {
    void digest(B bean);

    String getResult();
}
