
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_isValidTest {

    private static final String REGEX_1 = "^[A-Z]{3}$";
    private static final String REGEX_2 = "^[A-Z]{3} [A-Z]{3} \\d{3}$";
    private static final String REGEX_3 = "^\\d{3}$";
    private static final String[] MULTIPLE_REGEX = {REGEX_1, REGEX_2, REGEX_3};

    private RegexValidator multiple;
    private RegexValidator single1;
    private RegexValidator single2;
    private RegexValidator single3;

    @BeforeEach
    public void setUp() {
        multiple = new RegexValidator(MULTIPLE_REGEX, false);
        single1 = new RegexValidator(REGEX_1, false);
        single2 = new RegexValidator(REGEX_2, false);
        single3 = new RegexValidator(REGEX_3, false);
    }

    @Test
    public void testIsValidWithValidValue() {
        String value = "AAC FDE 321";
        assertTrue(multiple.isValid(value), "isValid() Multiple");
        assertFalse(single1.isValid(value), "isValid() 1st");
        assertTrue(single2.isValid(value), "isValid() 2nd");
        assertFalse(single3.isValid(value), "isValid() 3rd");
    }

    @Test
    public void testIsValidWithInvalidValue() {
        String value = "AAC*FDE*321";
        assertFalse(multiple.isValid(value), "isValid() Invalid");
        assertFalse(single1.isValid(value), "isValid() 1st");
        assertFalse(single2.isValid(value), "isValid() 2nd");
        assertFalse(single3.isValid(value), "isValid() 3rd");
    }

    @Test
    public void testIsValidWithNullValue() {
        String value = null;
        assertFalse(multiple.isValid(value), "isValid() Null");
        assertFalse(single1.isValid(value), "isValid() 1st");
        assertFalse(single2.isValid(value), "isValid() 2nd");
        assertFalse(single3.isValid(value), "isValid() 3rd");
    }

    @Test
    public void testIsValidWithEmptyValue() {
        String value = "";
        assertFalse(multiple.isValid(value), "isValid() Empty");
        assertFalse(single1.isValid(value), "isValid() 1st");
        assertFalse(single2.isValid(value), "isValid() 2nd");
        assertFalse(single3.isValid(value), "isValid() 3rd");
    }

    private void checkArray(String message, String[] expected, String[] actual) {
        if (expected == null) {
            assertNull(actual, message);
        } else {
            assertArrayEquals(expected, actual, message);
        }
    }
}
