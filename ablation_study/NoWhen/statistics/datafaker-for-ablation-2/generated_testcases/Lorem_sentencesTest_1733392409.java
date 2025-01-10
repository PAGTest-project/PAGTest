
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lorem_sentencesTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        lorem = new Lorem(faker);
    }

    @Test
    public void testSentencesWithCount() {
        int sentenceCount = 5;
        List<String> sentences = lorem.sentences(sentenceCount);
        assertEquals(sentenceCount, sentences.size());
        for (String sentence : sentences) {
            assertTrue(sentence.endsWith("."));
        }
    }

    @Test
    public void testSentencesWithZeroCount() {
        int sentenceCount = 0;
        List<String> sentences = lorem.sentences(sentenceCount);
        assertTrue(sentences.isEmpty());
    }

    @Test
    public void testSentencesWithNegativeCount() {
        int sentenceCount = -5;
        List<String> sentences = lorem.sentences(sentenceCount);
        assertTrue(sentences.isEmpty());
    }
}
