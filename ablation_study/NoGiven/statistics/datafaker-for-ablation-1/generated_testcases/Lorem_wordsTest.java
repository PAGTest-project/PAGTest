
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Lorem_wordsTest {
    private Lorem lorem;

    @BeforeEach
    public void setUp() {
        BaseProviders faker = new BaseProviders() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Provide a concrete implementation for the abstract method
            }
        };
        lorem = new Lorem(faker);
    }

    @Test
    public void testWordsWithZero() {
        List<String> result = lorem.words(0);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testWordsWithPositiveNumber() {
        int num = 5;
        List<String> result = lorem.words(num);
        assertEquals(num, result.size());
    }

    @Test
    public void testWordsWithNegativeNumber() {
        int num = -5;
        List<String> result = lorem.words(num);
        assertTrue(result.isEmpty());
    }
}
