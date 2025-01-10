
package org.jsoup.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ParseSettings_normalizeTagTest {

    @Test
    public void testNormalizeTag_PreserveCase() {
        ParseSettings settings = new ParseSettings(true, true);
        String result = settings.normalizeTag("  TestTag  ");
        assertEquals("TestTag", result);
    }

    @Test
    public void testNormalizeTag_NormalizeCase() {
        ParseSettings settings = new ParseSettings(false, false);
        String result = settings.normalizeTag("  TestTag  ");
        assertEquals("testtag", result);
    }
}
