
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.validator.routines.IBANValidator.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IBANValidator_setValidatorTest {

    private IBANValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new IBANValidator();
    }

    @Test
    public void testSetValidatorSingletonModification() {
        assertThrows(IllegalStateException.class, () -> {
            IBANValidator.DEFAULT_IBAN_VALIDATOR.setValidator("DE", 22, "DE\\d{20}");
        });
    }

    @Test
    public void testSetValidatorValid() {
        Validator result = validator.setValidator("DE", 22, "DE\\d{20}");
        assertNotNull(result);
        Validator newValidator = validator.getValidator("DE");
        assertNotNull(newValidator);
        assertEquals(22, newValidator.getIbanLength());
    }

    @Test
    public void testSetValidatorRemove() {
        validator.setValidator("DE", 22, "DE\\d{20}");
        Validator result = validator.setValidator("DE", -1, null);
        assertNotNull(result);
        Validator removedValidator = validator.getValidator("DE");
        assertNull(removedValidator);
    }

    @Test
    public void testSetValidatorInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("DE", 35, "DE\\d{20}");
        });
    }

    @Test
    public void testSetValidatorInvalidCountryCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("D", 22, "D\\d{20}");
        });
    }

    @Test
    public void testSetValidatorInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("DE", 22, "D\\d{20}");
        });
    }
}
