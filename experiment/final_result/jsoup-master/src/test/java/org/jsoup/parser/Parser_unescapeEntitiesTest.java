
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_unescapeEntitiesTest {

    @Test
    public void testUnescapeEntitiesInAttribute() {
        String input = "&lt;tag&gt;";
        String expected = "<tag>";
        String result = Parser.unescapeEntities(input, true);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntitiesInText() {
        String input = "&lt;tag&gt;";
        String expected = "<tag>";
        String result = Parser.unescapeEntities(input, false);
        assertEquals(expected, result);
    }
}
