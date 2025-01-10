
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class Internet_slugTest {

    @Test
    public void testSlugWithNullInputs() {
        Internet internet = new Internet(null);
        String result = internet.slug(null, null);
        String[] parts = result.split("_");
        assertEquals(2, parts.length);
    }

    @Test
    public void testSlugWithNonNullInputs() {
        Internet internet = new Internet(null);
        String result = internet.slug(List.of("word1", "word2"), "-");
        assertEquals("word1-word2", result);
    }

    @Test
    public void testSlugWithNonNullWordsAndNullGlue() {
        Internet internet = new Internet(null);
        String result = internet.slug(List.of("word1", "word2"), null);
        assertEquals("word1_word2", result);
    }

    @Test
    public void testSlugWithNullWordsAndNonNullGlue() {
        Internet internet = new Internet(null);
        String result = internet.slug(null, "-");
        String[] parts = result.split("-");
        assertEquals(2, parts.length);
    }
}
