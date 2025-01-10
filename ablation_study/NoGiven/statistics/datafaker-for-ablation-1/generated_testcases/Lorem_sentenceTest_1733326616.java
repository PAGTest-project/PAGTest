
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lorem_sentenceTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public Lorem lorem() {
                return new Lorem(this);
            }
        };
        lorem = faker.lorem();
    }

    @Test
    public void testSentenceWithFixedWordCount() {
        String sentence = lorem.sentence(3, 0);
        int wordCount = sentence.split("\\s+").length;
        assertEquals(4, wordCount);
    }

    @Test
    public void testSentenceWithRandomWordsAdded() {
        String sentence = lorem.sentence(3, 3);
        int wordCount = sentence.split("\\s+").length;
        assertTrue(wordCount >= 4 && wordCount <= 6);
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
    public void testSentenceWithRandomWordsAddedAndNegativeBase() {
        String sentence = lorem.sentence(-1, 3);
        int wordCount = sentence.split("\\s+").length;
        assertTrue(wordCount >= 0 && wordCount <= 3);
    }
}
