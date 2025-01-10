
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegexpUtils_escapeRegexTest {

    @Test
    void testEscapeRegex_NoEscapeNeeded() {
        String input = "no_special_characters";
        String expected = "no_special_characters";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }

    @Test
    void testEscapeRegex_EscapeNeeded() {
        String input = "special.$characters";
        String expected = "special\\.\\$characters";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }

    @Test
    void testEscapeRegex_AllEscapeNeeded() {
        String input = "^$(){}[].+*?\\";
        String expected = "\\^\\$\\(\\)\\{\\}\\[\\]\\.\\+\\*\\?\\\\";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }
}
