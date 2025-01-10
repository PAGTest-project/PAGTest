
package org.apache.commons.validator.routines;

import org.apache.commons.validator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IBANValidator_setValidatorTest {

    private IBANValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new IBANValidator();
    }

    @Test
    public void testSetValidator_SingletonModification() {
        IBANValidator defaultValidator = IBANValidator.getInstance();
        assertThrows(IllegalStateException.class, () -> {
            defaultValidator.setValidator("DE", 22, "DE\\d{20}");
        });
    }

    @Test
    public void testSetValidator_ValidInput() {
        org.apache.commons.validator.routines.IBANValidator.Validator result = validator.setValidator("DE", 22, "DE\\d{20}");
        assertNull(result); // No previous validator for "DE"

        org.apache.commons.validator.routines.IBANValidator.Validator newValidator = validator.getValidator("DE");
        assertNotNull(newValidator);
        assertEquals("DE", newValidator.countryCode);
        assertEquals(22, newValidator.ibanLength);
    }

    @Test
    public void testSetValidator_RemoveValidator() {
        validator.setValidator("DE", 22, "DE\\d{20}");
        org.apache.commons.validator.routines.IBANValidator.Validator result = validator.setValidator("DE", -1, null);
        assertNotNull(result); // Previous validator for "DE" should be returned

        org.apache.commons.validator.routines.IBANValidator.Validator removedValidator = validator.getValidator("DE");
        assertNull(removedValidator);
    }
}
