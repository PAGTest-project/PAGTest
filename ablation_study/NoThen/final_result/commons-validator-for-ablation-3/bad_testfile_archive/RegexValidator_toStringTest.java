
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegexValidator_toStringTest {

    private static final String REGEX_1 = "AAC";
    private static final String REGEX_2 = "FDE";
    private static final String REGEX_3 = "321";
    private static final String[] MULTIPLE_REGEX = {REGEX_1, REGEX_2, REGEX_3};

    private RegexValidator multiple;

    @BeforeEach
    public void setUp() {
        multiple = new RegexValidator(MULTIPLE_REGEX, false);
    }

    @Test
    public void testToStringSingleRegex() {
        RegexValidator single = new RegexValidator(REGEX_1, false);
        assertEquals("RegexValidator{AAC}", single.toString());
    }

    @Test
    public void testToStringMultipleRegex() {
        assertEquals("RegexValidator{AAC,FDE,321}", multiple.toString());
    }

    @Test
    public void testToStringEmptyRegex() {
        RegexValidator empty = new RegexValidator(new String[]{}, false);
        assertEquals("RegexValidator{}", empty.toString());
    }

    @Test
    public void testToStringNullRegex() {
        RegexValidator nullRegex = new RegexValidator(new String[]{null}, false);
        assertEquals("RegexValidator{}", nullRegex.toString());
    }
}
