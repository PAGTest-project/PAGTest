
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CodeValidator_validateTest {

    private CodeValidator codeValidator;
    private CheckDigit checkDigit;

    @BeforeEach
    public void setUp() {
        checkDigit = new EAN13CheckDigit();
        codeValidator = new CodeValidator("\\d{13}", 13, 13, checkDigit);
    }

    @Test
    public void testValidateNullInput() {
        assertNull(codeValidator.validate(null));
    }

    @Test
    public void testValidateEmptyInput() {
        assertNull(codeValidator.validate(""));
    }

    @Test
    public void testValidateInvalidRegex() {
        assertNull(codeValidator.validate("12345678901234")); // 14 digits
    }

    @Test
    public void testValidateInvalidLength() {
        assertNull(codeValidator.validate("123456789012")); // 12 digits
    }

    @Test
    public void testValidateInvalidCheckDigit() {
        assertNull(codeValidator.validate("1234567890128")); // Invalid check digit
    }

    @Test
    public void testValidateValidInput() {
        assertEquals("1234567890128", codeValidator.validate("1234567890128"));
    }
}
