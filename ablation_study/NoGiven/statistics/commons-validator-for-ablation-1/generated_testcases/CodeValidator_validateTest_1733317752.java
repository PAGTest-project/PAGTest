
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.apache.commons.validator.routines.checkdigit.CheckDigit;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CodeValidator_validateTest {

    private CodeValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new CodeValidator((String) null, -1, -1, (CheckDigit) null);
    }

    @Test
    public void testValidateNullInput() {
        assertNull(validator.validate(null), "Null input should return null");
    }

    @Test
    public void testValidateEmptyInput() {
        assertNull(validator.validate(""), "Empty input should return null");
    }

    @Test
    public void testValidateValidCodeWithoutRegexValidator() {
        final String validCode = "1234567890123";
        assertEquals(validCode, validator.validate(validCode), "Valid code should return the code");
    }

    @Test
    public void testValidateInvalidCodeWithoutRegexValidator() {
        final String invalidCode = "12345";
        assertEquals(invalidCode, validator.validate(invalidCode), "Invalid code should return the code");
    }

    @Test
    public void testValidateValidCodeWithRegexValidator() {
        RegexValidator regexValidator = new RegexValidator("\\d{13}");
        validator = new CodeValidator(regexValidator, 13, 13, (CheckDigit) null);
        final String validCode = "1234567890123";
        assertEquals(validCode, validator.validate(validCode), "Valid code should return the code");
    }

    @Test
    public void testValidateInvalidCodeWithRegexValidator() {
        RegexValidator regexValidator = new RegexValidator("\\d{13}");
        validator = new CodeValidator(regexValidator, 13, 13, (CheckDigit) null);
        final String invalidCode = "12345";
        assertNull(validator.validate(invalidCode), "Invalid code should return null");
    }

    @Test
    public void testValidateValidCodeWithCheckDigit() {
        validator = new CodeValidator((String) null, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        final String validCode = "9781930110991";
        assertEquals(validCode, validator.validate(validCode), "Valid code should return the code");
    }

    @Test
    public void testValidateInvalidCodeWithCheckDigit() {
        validator = new CodeValidator((String) null, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        final String invalidCode = "9781930110992";
        assertNull(validator.validate(invalidCode), "Invalid code should return null");
    }

    @Test
    public void testValidateValidCodeWithRegexValidatorAndCheckDigit() {
        RegexValidator regexValidator = new RegexValidator("\\d{13}");
        validator = new CodeValidator(regexValidator, 13, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        final String validCode = "9781930110991";
        assertEquals(validCode, validator.validate(validCode), "Valid code should return the code");
    }

    @Test
    public void testValidateInvalidCodeWithRegexValidatorAndCheckDigit() {
        RegexValidator regexValidator = new RegexValidator("\\d{13}");
        validator = new CodeValidator(regexValidator, 13, 13, EAN13CheckDigit.EAN13_CHECK_DIGIT);
        final String invalidCode = "9781930110992";
        assertNull(validator.validate(invalidCode), "Invalid code should return null");
    }
}
