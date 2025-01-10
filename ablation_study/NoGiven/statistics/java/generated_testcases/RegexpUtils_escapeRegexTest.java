
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegexpUtils_escapeRegexTest {

    @Test
    void testEscapeRegex_NoEscapeNeeded() {
        String input = "no_escape_needed";
        String expected = "no_escape_needed";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }

    @Test
    void testEscapeRegex_EscapeNeeded() {
        String input = "escape.{}[]()\\*+?|^$";
        String expected = "escape\\.\\{\\}\\[\\]\\(\\)\\\\\\\\*\\+\\?\\|\\^\\$";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }
}
