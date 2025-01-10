
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lorem_sentenceTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders();
        lorem = new Lorem(faker);
    }

    @Test
    public void testSentenceWithFixedWordCount() {
        String sentence = lorem.sentence(5, 0);
        String[] words = sentence.split(" ");
        assertEquals(6, words.length); // 5 words + 1 capitalized word
    }

    @Test
    public void testSentenceWithRandomWords() {
        String sentence = lorem.sentence(5, 3);
        String[] words = sentence.split(" ");
        assertTrue(words.length >= 6 && words.length <= 9); // 5 words + 1 to 4 random words
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
    public void testSentenceWithRandomWordsToAdd() {
        String sentence = lorem.sentence(3, 5);
        String[] words = sentence.split(" ");
        assertTrue(words.length >= 4 && words.length <= 9); // 3 words + 1 to 6 random words
    }
}
