
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_toStringTest {

    private RegexValidator singlePatternValidator;
    private RegexValidator multiplePatternValidator;

    @BeforeEach
    public void setUp() {
        singlePatternValidator = new RegexValidator("^[A-Z]*$");
        multiplePatternValidator = new RegexValidator("^[A-Z]*$", "^[0-9]*$");
    }

    @Test
    public void testToStringSinglePattern() {
        assertEquals("RegexValidator{^[A-Z]*$}", singlePatternValidator.toString());
    }

    @Test
    public void testToStringMultiplePatterns() {
        assertEquals("RegexValidator{^[A-Z]*$,^[0-9]*$}", multiplePatternValidator.toString());
    }

    @Test
    public void testToStringEmptyPatterns() {
        try {
            new RegexValidator();
        } catch (IllegalArgumentException e) {
            assertEquals("Regular expressions are missing", e.getMessage());
        }
    }

    @Test
    public void testToStringNullPattern() {
        try {
            new RegexValidator((String) null);
        } catch (IllegalArgumentException e) {
            assertEquals("Regular expression[0] is missing", e.getMessage());
        }
    }
}
