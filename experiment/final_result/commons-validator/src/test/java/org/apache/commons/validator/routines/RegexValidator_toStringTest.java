
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegexValidator_toStringTest {

    private RegexValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new RegexValidator("^[a-z]+$", "^[A-Z]+$");
    }

    @Test
    public void testToString() {
        String expected = "RegexValidator{^[a-z]+$,^[A-Z]+$}";
        assertEquals(expected, validator.toString(), "toString() should return the correct pattern representation");
    }
}
