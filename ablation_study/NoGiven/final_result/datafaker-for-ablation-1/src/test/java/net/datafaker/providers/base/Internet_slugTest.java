
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Internet_slugTest {

    private final BaseFaker faker = new BaseFaker();

    @Test
    public void testSlugWithNonNullWordsAndGlue() {
        Internet internet = new Internet(faker);
        List<String> words = List.of("hello", "world");
        String glue = "-";
        String expected = "hello-world";
        String result = internet.slug(words, glue);
        assertEquals(expected, result);
    }

    @Test
    public void testSlugWithNullWordsAndNonNullGlue() {
        Internet internet = new Internet(faker);
        String glue = "-";
        String result = internet.slug(null, glue);
        assertEquals(2, result.split(glue).length);
    }

    @Test
    public void testSlugWithNonNullWordsAndNullGlue() {
        Internet internet = new Internet(faker);
        List<String> words = List.of("hello", "world");
        String expected = "hello_world";
        String result = internet.slug(words, null);
        assertEquals(expected, result);
    }

    @Test
    public void testSlugWithNullWordsAndNullGlue() {
        Internet internet = new Internet(faker);
        String result = internet.slug(null, null);
        assertEquals(2, result.split("_").length);
    }
}
