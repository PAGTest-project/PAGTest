
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_validateTest {

    private static final String REGEX_1 = "^([a-z]+)$";
    private static final String REGEX_2 = "^([A-Z]+)$";
    private static final String REGEX_3 = "^([0-9]+)$";
    private static final String[] MULTIPLE_REGEX = {REGEX_1, REGEX_2, REGEX_3};

    private RegexValidator multiple;
    private RegexValidator single1;
    private RegexValidator single2;
    private RegexValidator single3;

    @BeforeEach
    public void setUp() {
        multiple = new RegexValidator(MULTIPLE_REGEX);
        single1 = new RegexValidator(REGEX_1);
        single2 = new RegexValidator(REGEX_2);
        single3 = new RegexValidator(REGEX_3);
    }

    @Test
    public void testValidateMultipleRegex() {
        String value = "aac FDE 321";
        String expect = "aacFDE321";

        assertEquals(expect, multiple.validate(value), "validate() Multiple");
        assertNull(single1.validate(value), "validate() 1st");
        assertEquals(expect, single2.validate(value), "validate() 2nd");
        assertNull(single3.validate(value), "validate() 3rd");
    }

    @Test
    public void testValidateSingleRegex() {
        String value1 = "aac";
        String value2 = "FDE";
        String value3 = "321";

        assertEquals("aac", single1.validate(value1), "validate() 1st");
        assertEquals("FDE", single2.validate(value2), "validate() 2nd");
        assertEquals("321", single3.validate(value3), "validate() 3rd");
    }

    @Test
    public void testValidateNullValue() {
        assertNull(multiple.validate(null), "validate() null value");
        assertNull(single1.validate(null), "validate() null value");
        assertNull(single2.validate(null), "validate() null value");
        assertNull(single3.validate(null), "validate() null value");
    }

    @Test
    public void testValidateInvalidValue() {
        String value = "AAC*FDE*321";

        assertNull(multiple.validate(value), "validate() Invalid");
        assertNull(single1.validate(value), "validate() Invalid");
        assertNull(single2.validate(value), "validate() Invalid");
        assertNull(single3.validate(value), "validate() Invalid");
    }
}
