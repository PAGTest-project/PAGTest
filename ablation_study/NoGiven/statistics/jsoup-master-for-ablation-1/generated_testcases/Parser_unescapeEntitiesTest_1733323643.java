
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parser_unescapeEntitiesTest {

    @Test
    public void testUnescapeEntities() {
        String input = "&lt;div&gt;";
        String expected = "<div>";
        String actual = Parser.unescapeEntities(input, false);
        assertEquals(expected, actual);
    }
}
