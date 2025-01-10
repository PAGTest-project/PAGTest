
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANValidator_isValidTest {

    private IBANValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new IBANValidator();
    }

    @Test
    public void testIsValid_ValidIBAN() {
        String validIBAN = "DE89370400440532013000";
        assertTrue(validator.isValid(validIBAN));
    }

    @Test
    public void testIsValid_InvalidIBAN_WrongLength() {
        String invalidIBAN = "DE8937040044053201300";
        assertFalse(validator.isValid(invalidIBAN));
    }

    @Test
    public void testIsValid_InvalidIBAN_WrongFormat() {
        String invalidIBAN = "DE8937040044053201300A";
        assertFalse(validator.isValid(invalidIBAN));
    }

    @Test
    public void testIsValid_InvalidIBAN_NoValidator() {
        String invalidIBAN = "ZZ89370400440532013000";
        assertFalse(validator.isValid(invalidIBAN));
    }

    @Test
    public void testIsValid_NullIBAN() {
        assertFalse(validator.isValid(null));
    }
}
