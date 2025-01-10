
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreditCardValidator_validateTest {

    private CreditCardValidator creditCardValidator;

    @BeforeEach
    public void setUp() {
        creditCardValidator = new CreditCardValidator();
    }

    @Test
    public void testValidateNullCard() {
        assertNull(creditCardValidator.validate(null));
    }

    @Test
    public void testValidateBlankCard() {
        assertNull(creditCardValidator.validate(""));
    }

    @Test
    public void testValidateValidVisa() {
        assertNotNull(creditCardValidator.validate("4111111111111111"));
    }

    @Test
    public void testValidateValidAmex() {
        assertNotNull(creditCardValidator.validate("378282246310005"));
    }

    @Test
    public void testValidateValidMastercard() {
        assertNotNull(creditCardValidator.validate("5105105105105100"));
    }

    @Test
    public void testValidateValidDiscover() {
        assertNotNull(creditCardValidator.validate("6011111111111117"));
    }

    @Test
    public void testValidateInvalidCard() {
        assertNull(creditCardValidator.validate("1234567812345678"));
    }
}
