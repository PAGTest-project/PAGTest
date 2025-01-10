
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_unescapeEntitiesTest {

    @Test
    public void testUnescapeEntitiesInAttribute() {
        String input = "&lt;a href=&quot;example.com&quot;&gt;Link&lt;/a&gt;";
        String expected = "<a href=\"example.com\">Link</a>";
        String result = Parser.unescapeEntities(input, true);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntitiesNotInAttribute() {
        String input = "&lt;a href=&quot;example.com&quot;&gt;Link&lt;/a&gt;";
        String expected = "<a href=\"example.com\">Link</a>";
        String result = Parser.unescapeEntities(input, false);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntitiesWithInvalidEntity() {
        String input = "&invalid;";
        String expected = "&invalid;";
        String result = Parser.unescapeEntities(input, false);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntitiesWithMixedEntities() {
        String input = "Hello &lt;world&gt; &amp; &quot;universe&quot;";
        String expected = "Hello <world> & \"universe\"";
        String result = Parser.unescapeEntities(input, false);
        assertEquals(expected, result);
    }
}
