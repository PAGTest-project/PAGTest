
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardValidator_isValidTest {

    private CreditCardValidator creditCardValidator;

    @BeforeEach
    public void setUp() {
        creditCardValidator = new CreditCardValidator();
    }

    @Test
    public void testIsValidWithNullCard() {
        assertFalse(creditCardValidator.isValid(null));
    }

    @Test
    public void testIsValidWithBlankCard() {
        assertFalse(creditCardValidator.isValid(""));
    }

    @Test
    public void testIsValidWithValidVisa() {
        assertTrue(creditCardValidator.isValid("4111111111111111"));
    }

    @Test
    public void testIsValidWithValidAmex() {
        assertTrue(creditCardValidator.isValid("378282246310005"));
    }

    @Test
    public void testIsValidWithValidMastercard() {
        assertTrue(creditCardValidator.isValid("5105105105105100"));
    }

    @Test
    public void testIsValidWithValidDiscover() {
        assertTrue(creditCardValidator.isValid("6011111111111117"));
    }

    @Test
    public void testIsValidWithInvalidCard() {
        assertFalse(creditCardValidator.isValid("1234567812345678"));
    }
}
