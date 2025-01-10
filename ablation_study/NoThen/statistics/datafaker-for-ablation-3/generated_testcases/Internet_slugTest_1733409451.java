
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class Internet_slugTest {

    private final BaseFaker faker = new BaseFaker();
    private final Internet internet = new Internet(faker);

    @Test
    public void testSlugWithDefaultGlueAndWords() {
        String result = internet.slug(null, null);
        assertEquals(4, result.length()); // Assuming faker.lorem().words(2) returns 2 words of length 2 each
    }

    @Test
    public void testSlugWithCustomGlueAndWords() {
        String result = internet.slug(List.of("word1", "word2"), "-");
        assertEquals("word1-word2", result);
    }

    @Test
    public void testSlugWithCustomGlueAndNullWords() {
        String result = internet.slug(null, "-");
        assertEquals(7, result.length()); // Assuming faker.lorem().words(2) returns 2 words of length 2 each
    }

    @Test
    public void testSlugWithNullGlueAndCustomWords() {
        String result = internet.slug(List.of("word1", "word2"), null);
        assertEquals("word1_word2", result);
    }
}
