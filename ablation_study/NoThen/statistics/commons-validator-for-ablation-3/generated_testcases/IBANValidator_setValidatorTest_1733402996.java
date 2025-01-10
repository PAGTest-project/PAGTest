
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

    private IBANValidator ibanValidator;

    @BeforeEach
    public void setUp() {
        ibanValidator = new IBANValidator();
    }

    @Test
    public void testSetValidatorSuccess() {
        Validator validator = ibanValidator.setValidator("XX", 22, "XX\\d{20}");
        assertNull(validator);
        Validator retrievedValidator = ibanValidator.getValidator("XX");
        assertNotNull(retrievedValidator);
        assertEquals(22, retrievedValidator.getIbanLength());
    }

    @Test
    public void testSetValidatorRemove() {
        ibanValidator.setValidator("XX", 22, "XX\\d{20}");
        Validator removedValidator = ibanValidator.setValidator("XX", -1, null);
        assertNotNull(removedValidator);
        Validator retrievedValidator = ibanValidator.getValidator("XX");
        assertNull(retrievedValidator);
    }

    @Test
    public void testSetValidatorSingletonModification() {
        IBANValidator defaultValidator = IBANValidator.getInstance();
        assertThrows(IllegalStateException.class, () -> {
            defaultValidator.setValidator("XX", 22, "XX\\d{20}");
        });
    }

    @Test
    public void testSetValidatorInvalidCountryCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            ibanValidator.setValidator("x1", 22, "x1\\d{20}");
        });
    }

    @Test
    public void testSetValidatorInvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            ibanValidator.setValidator("XX", 35, "XX\\d{20}");
        });
    }

    @Test
    public void testSetValidatorInvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            ibanValidator.setValidator("XX", 22, "\\d{20}");
        });
    }
}
