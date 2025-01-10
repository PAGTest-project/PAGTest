
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parser_unescapeEntitiesTest {

    @Test
    public void testUnescapeEntities_InAttribute() {
        String input = "&lt;a href=&quot;test&quot;&gt;link&lt;/a&gt;";
        String expected = "<a href=\"test\">link</a>";
        String result = Parser.unescapeEntities(input, true);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntities_NotInAttribute() {
        String input = "&lt;a href=&quot;test&quot;&gt;link&lt;/a&gt;";
        String expected = "<a href=\"test\">link</a>";
        String result = Parser.unescapeEntities(input, false);
        assertEquals(expected, result);
    }
}
