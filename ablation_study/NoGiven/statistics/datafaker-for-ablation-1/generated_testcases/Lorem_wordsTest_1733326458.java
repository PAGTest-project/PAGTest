
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
            // Provide a concrete implementation for the abstract methods if needed
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
