
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
    public void testSetValidator_Success() {
        Validator newValidator = validator.setValidator("XX", 22, "XX\\d{20}");
        assertNull(newValidator);

        Validator retrievedValidator = validator.getValidator("XX");
        assertNotNull(retrievedValidator);
        assertEquals(22, retrievedValidator.getIbanLength());
        assertTrue(retrievedValidator.getRegexValidator().isValid("XX12345678901234567890"));
    }

    @Test
    public void testSetValidator_Remove() {
        validator.setValidator("XX", 22, "XX\\d{20}");
        Validator removedValidator = validator.setValidator("XX", -1, null);
        assertNotNull(removedValidator);

        Validator retrievedValidator = validator.getValidator("XX");
        assertNull(retrievedValidator);
    }

    @Test
    public void testSetValidator_SingletonModification() {
        IBANValidator defaultValidator = IBANValidator.getInstance();
        assertThrows(IllegalStateException.class, () -> {
            defaultValidator.setValidator("XX", 22, "XX\\d{20}");
        });
    }

    @Test
    public void testSetValidator_InvalidCountryCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("xX", 22, "xX\\d{20}");
        });
    }

    @Test
    public void testSetValidator_InvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("XX", 35, "XX\\d{20}");
        });
    }

    @Test
    public void testSetValidator_InvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("XX", 22, "\\d{20}");
        });
    }
}
