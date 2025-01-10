
package io.cucumber.cucumberexpressions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RegexpUtils_escapeRegexTest {

    @Test
    void testEscapeRegex_NoEscapeNeeded() {
        String input = "no_special_chars";
        String expected = "no_special_chars";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }

    @Test
    void testEscapeRegex_EscapeNeeded() {
        String input = "special.chars$";
        String expected = "special\\.chars\\$";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }

    @Test
    void testEscapeRegex_MixedCharacters() {
        String input = "normal.chars$and_special.chars$";
        String expected = "normal\\.chars\\$and_special\\.chars\\$";
        assertEquals(expected, RegexpUtils.escapeRegex(input));
    }
}
