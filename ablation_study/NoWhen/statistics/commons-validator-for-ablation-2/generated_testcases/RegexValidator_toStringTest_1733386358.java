
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegexValidator_toStringTest {

    private static final String REGEX_1 = "abc";
    private static final String REGEX_2 = "def";
    private static final String[] MULTIPLE_REGEX = {REGEX_1, REGEX_2};

    private RegexValidator regexValidator;

    @BeforeEach
    public void setUp() {
        regexValidator = new RegexValidator(MULTIPLE_REGEX);
    }

    @Test
    public void testToStringSingleRegex() {
        RegexValidator singleRegexValidator = new RegexValidator(REGEX_1);
        assertEquals("RegexValidator{abc}", singleRegexValidator.toString());
    }

    @Test
    public void testToStringMultipleRegex() {
        assertEquals("RegexValidator{abc,def}", regexValidator.toString());
    }

    @Test
    public void testToStringEmptyRegex() {
        RegexValidator emptyRegexValidator = new RegexValidator(new String[]{});
        assertEquals("RegexValidator{}", emptyRegexValidator.toString());
    }
}
