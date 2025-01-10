
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lorem_sentenceTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseFaker();
        lorem = new Lorem(faker);
    }

    @Test
    public void testSentenceWithFixedWordCount() {
        String sentence = lorem.sentence(5, 0);
        int wordCount = sentence.split("\\s+").length;
        assertEquals(5, wordCount);
    }

    @Test
    public void testSentenceWithRandomWordsAdded() {
        String sentence = lorem.sentence(5, 3);
        int wordCount = sentence.split("\\s+").length;
        assertTrue(wordCount >= 5 && wordCount <= 8);
    }

    @Test
    public void testSentenceWithZeroWordCount() {
        String sentence = lorem.sentence(0, 0);
        assertEquals(".", sentence);
    }

    @Test
    public void testSentenceWithNegativeWordCount() {
        String sentence = lorem.sentence(-1, 0);
        assertEquals(".", sentence);
    }

    @Test
    public void testSentenceWithRandomWordsAddedAndZeroBase() {
        String sentence = lorem.sentence(0, 5);
        int wordCount = sentence.split("\\s+").length;
        assertTrue(wordCount >= 0 && wordCount <= 5);
    }
}
