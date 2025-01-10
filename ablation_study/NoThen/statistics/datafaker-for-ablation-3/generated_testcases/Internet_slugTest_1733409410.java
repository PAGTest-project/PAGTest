
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class Internet_slugTest {

    @Test
    public void testSlugWithDefaultGlueAndWords() {
        Internet internet = new Internet(null);
        String result = internet.slug(null, null);
        assertEquals(4, result.length()); // Assuming faker.lorem().words(2) returns 2 words of length 2 each
    }

    @Test
    public void testSlugWithCustomGlueAndWords() {
        Internet internet = new Internet(null);
        String result = internet.slug(List.of("word1", "word2"), "-");
        assertEquals("word1-word2", result);
    }

    @Test
    public void testSlugWithCustomGlueAndNullWords() {
        Internet internet = new Internet(null);
        String result = internet.slug(null, "-");
        assertEquals(7, result.length()); // Assuming faker.lorem().words(2) returns 2 words of length 2 each
    }

    @Test
    public void testSlugWithNullGlueAndCustomWords() {
        Internet internet = new Internet(null);
        String result = internet.slug(List.of("word1", "word2"), null);
        assertEquals("word1_word2", result);
    }
}
