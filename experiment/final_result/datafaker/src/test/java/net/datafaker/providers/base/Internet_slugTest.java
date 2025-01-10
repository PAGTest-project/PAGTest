
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Internet_slugTest {

    @Test
    public void testSlugWithDefaultGlueAndWords() {
        BaseFaker faker = new BaseFaker();
        Internet internet = new Internet(faker);
        String result = internet.slug(null, null);
        String[] parts = result.split("_");
        assertEquals(2, parts.length);
    }

    @Test
    public void testSlugWithCustomGlueAndWords() {
        BaseFaker faker = new BaseFaker();
        Internet internet = new Internet(faker);
        String result = internet.slug(List.of("word1", "word2"), "-");
        assertEquals("word1-word2", result);
    }

    @Test
    public void testSlugWithCustomGlueAndNullWords() {
        BaseFaker faker = new BaseFaker();
        Internet internet = new Internet(faker);
        String result = internet.slug(null, "-");
        String[] parts = result.split("-");
        assertEquals(2, parts.length);
    }

    @Test
    public void testSlugWithNullGlueAndCustomWords() {
        BaseFaker faker = new BaseFaker();
        Internet internet = new Internet(faker);
        String result = internet.slug(List.of("word1", "word2"), null);
        assertEquals("word1_word2", result);
    }
}
