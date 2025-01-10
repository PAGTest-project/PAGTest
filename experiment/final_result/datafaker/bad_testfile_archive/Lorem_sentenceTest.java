
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
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Dummy implementation to satisfy the abstract method
            }

            @Override
            public FakeValuesService fakeValuesService() {
                return new FakeValuesService();
            }

            @Override
            public RandomService random() {
                return new RandomService();
            }
        };
        lorem = new Lorem(faker);
    }

    @Test
    public void testSentenceWithFixedWordCount() {
        String sentence = lorem.sentence(3, 0);
        String[] words = sentence.split(" ");
        assertEquals(4, words.length); // 3 words + 1 capitalized word
        assertTrue(Character.isUpperCase(words[0].charAt(0)));
    }

    @Test
    public void testSentenceWithRandomWords() {
        String sentence = lorem.sentence(3, 3);
        String[] words = sentence.split(" ");
        assertTrue(words.length >= 4 && words.length <= 6); // 3 words + 1 to 3 random words
        assertTrue(Character.isUpperCase(words[0].charAt(0)));
    }

    @Test
    public void testSentenceWithZeroWordCount() {
        String sentence = lorem.sentence(0, 0);
        assertEquals(".", sentence);
    }
}
