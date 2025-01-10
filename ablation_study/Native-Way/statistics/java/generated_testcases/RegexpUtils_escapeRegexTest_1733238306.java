
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegexpUtils_escapeRegexTest {

    @Test
    void testEscapeRegex_NoSpecialChars() {
        String input = "hello world";
        String expected = "hello world";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }

    @Test
    void testEscapeRegex_WithSpecialChars() {
        String input = "hello.world$";
        String expected = "hello\\.world\\$";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }

    @Test
    void testEscapeRegex_AllSpecialChars() {
        String input = "^$(){}[].+*?\\";
        String expected = "\\^\\$\\(\\)\\{\\}\\[\\]\\.\\+\\*\\?\\\\";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }
}
