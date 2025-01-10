
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_toStringTest {

    private static final String[] MULTIPLE_REGEX = {"^[a-z]+$", "^[0-9]+$", "^[A-Z]+$"};
    private RegexValidator regexValidator;

    @BeforeEach
    public void setUp() {
        regexValidator = new RegexValidator(MULTIPLE_REGEX);
    }

    @Test
    public void testToStringWithMultipleRegex() {
        String expected = "RegexValidator{^[a-z]+$,^[0-9]+$,^[A-Z]+$}";
        assertEquals(expected, regexValidator.toString());
    }

    @Test
    public void testToStringWithSingleRegex() {
        regexValidator = new RegexValidator("^[a-z]+$");
        String expected = "RegexValidator{^[a-z]+$}";
        assertEquals(expected, regexValidator.toString());
    }

    @Test
    public void testToStringWithEmptyRegex() {
        regexValidator = new RegexValidator(new String[]{""});
        String expected = "RegexValidator{}";
        assertEquals(expected, regexValidator.toString());
    }
}
