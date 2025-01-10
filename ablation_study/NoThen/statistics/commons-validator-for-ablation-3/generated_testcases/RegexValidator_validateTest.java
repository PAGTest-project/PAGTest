
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_validateTest {

    private static final String REGEX_1 = "^[A-Z]{3}$";
    private static final String REGEX_2 = "^[A-Z]{3}\\s[A-Z]{3}\\s\\d{3}$";
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
    public void testValidateWithValidValue() {
        String value = "AAC FDE 321";
        String expect = "AACFDE321";

        assertEquals(expect, multiple.validate(value), "validate() Multiple");
        assertNull(single1.validate(value), "validate() 1st");
        assertEquals(expect, single2.validate(value), "validate() 2nd");
        assertNull(single3.validate(value), "validate() 3rd");
    }

    @Test
    public void testValidateWithInvalidValue() {
        String value = "AAC*FDE*321";

        assertNull(multiple.validate(value), "validate() Invalid");
        assertNull(single1.validate(value), "validate() 1st");
        assertNull(single2.validate(value), "validate() 2nd");
        assertNull(single3.validate(value), "validate() 3rd");
    }

    @Test
    public void testValidateWithNullValue() {
        String value = null;

        assertNull(multiple.validate(value), "validate() Null");
        assertNull(single1.validate(value), "validate() 1st");
        assertNull(single2.validate(value), "validate() 2nd");
        assertNull(single3.validate(value), "validate() 3rd");
    }

    @Test
    public void testValidateWithSingleGroup() {
        String value = "ABC";
        String expect = "ABC";

        assertEquals(expect, single1.validate(value), "validate() Single Group");
        assertNull(single2.validate(value), "validate() 2nd");
        assertNull(single3.validate(value), "validate() 3rd");
    }

    @Test
    public void testValidateWithMultipleGroups() {
        String value = "ABC DEF 123";
        String expect = "ABCDEF123";

        assertEquals(expect, single2.validate(value), "validate() Multiple Groups");
        assertNull(single1.validate(value), "validate() 1st");
        assertNull(single3.validate(value), "validate() 3rd");
    }
}
