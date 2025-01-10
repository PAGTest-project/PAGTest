
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_unescapeEntitiesTest {

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testUnescapeEntities_InAttribute() {
        String input = "&lt;tag&gt;";
        String expected = "<tag>";
        String result = Parser.unescapeEntities(input, true);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntities_NotInAttribute() {
        String input = "&lt;tag&gt;";
        String expected = "<tag>";
        String result = Parser.unescapeEntities(input, false);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntities_EmptyString() {
        String input = "";
        String expected = "";
        String result = Parser.unescapeEntities(input, true);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntities_NoEntities() {
        String input = "no entities here";
        String expected = "no entities here";
        String result = Parser.unescapeEntities(input, true);
        assertEquals(expected, result);
    }

    @Test
    public void testUnescapeEntities_MixedEntities() {
        String input = "a &lt; b &amp; c &gt; d";
        String expected = "a < b & c > d";
        String result = Parser.unescapeEntities(input, true);
        assertEquals(expected, result);
    }
}
