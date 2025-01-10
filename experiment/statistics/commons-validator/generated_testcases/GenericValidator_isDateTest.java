
package org.apache.commons.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericValidator_isDateTest {

    @BeforeEach
    public void setUp() {
        // No setup required for static methods
    }

    @Test
    public void testIsDateValid() {
        String value = "2023-10-05";
        String datePattern = "yyyy-MM-dd";
        boolean strict = true;
        assertTrue(GenericValidator.isDate(value, datePattern, strict));
    }

    @Test
    public void testIsDateInvalid() {
        String value = "2023-10-05";
        String datePattern = "MM/dd/yyyy";
        boolean strict = true;
        assertFalse(GenericValidator.isDate(value, datePattern, strict));
    }
}
