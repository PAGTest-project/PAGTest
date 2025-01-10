
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.apache.commons.validator.Validator;

public class IBANValidator_setValidatorTest {

    private IBANValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new IBANValidator();
    }

    @Test
    public void testSetValidator_SingletonModification() {
        assertThrows(IllegalStateException.class, () -> {
            IBANValidator.DEFAULT_IBAN_VALIDATOR.setValidator("GB", 22, "GB\\d{2}[A-Z]{4}\\d{14}");
        });
    }

    @Test
    public void testSetValidator_ValidInput() {
        IBANValidator.Validator newValidator = new IBANValidator.Validator("GB", 22, "GB\\d{2}[A-Z]{4}\\d{14}");
        IBANValidator.Validator oldValidator = validator.setValidator("GB", 22, "GB\\d{2}[A-Z]{4}\\d{14}");
        assertNotNull(oldValidator);
        assertEquals(newValidator.getIbanLength(), validator.getValidator("GB").getIbanLength());
    }

    @Test
    public void testSetValidator_RemoveValidator() {
        IBANValidator.Validator oldValidator = validator.setValidator("GB", -1, null);
        assertNotNull(oldValidator);
        assertNull(validator.getValidator("GB"));
    }

    @Test
    public void testSetValidator_InvalidCountryCode() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("gb", 22, "gb\\d{2}[A-Z]{4}\\d{14}");
        });
    }

    @Test
    public void testSetValidator_InvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("GB", 35, "GB\\d{2}[A-Z]{4}\\d{14}");
        });
    }

    @Test
    public void testSetValidator_InvalidFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            validator.setValidator("GB", 22, "GB\\d{2}[A-Z]{4}\\d{14}X");
        });
    }
}
