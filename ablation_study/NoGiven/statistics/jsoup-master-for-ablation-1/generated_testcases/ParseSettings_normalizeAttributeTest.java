
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseSettings_normalizeAttributeTest {

    @Test
    public void testNormalizeAttribute_PreserveCase() {
        ParseSettings settings = new ParseSettings(true, true);
        String result = settings.normalizeAttribute("  TestAttribute  ");
        assertEquals("TestAttribute", result);
    }

    @Test
    public void testNormalizeAttribute_DoNotPreserveCase() {
        ParseSettings settings = new ParseSettings(true, false);
        String result = settings.normalizeAttribute("  TestAttribute  ");
        assertEquals("testattribute", result);
    }
}
