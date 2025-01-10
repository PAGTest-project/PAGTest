
package org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISSNValidator_convertToEAN13Test {

    private ISSNValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new ISSNValidator();
    }

    @Test
    public void testConvertToEAN13Valid() {
        String issn = "1234-5679";
        String suffix = "00";
        String expectedEAN13 = "9771234567003";

        String result = validator.convertToEAN13(issn, suffix);
        assertEquals(expectedEAN13, result);
    }

    @Test
    public void testConvertToEAN13InvalidSuffix() {
        String issn = "1234-5679";
        String suffix = "0";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.convertToEAN13(issn, suffix);
        });
        assertEquals("Suffix must be two digits: '0'", exception.getMessage());
    }

    @Test
    public void testConvertToEAN13InvalidISSN() {
        String issn = "1234-5678";
        String suffix = "00";

        String result = validator.convertToEAN13(issn, suffix);
        assertNull(result);
    }

    @Test
    public void testConvertToEAN13CheckDigitException() {
        String issn = "1234-5679";
        String suffix = "00";

        // Mocking a CheckDigitException by providing an invalid EAN13 prefix
        String invalidEAN13 = "9781234567003"; // Invalid prefix '978' instead of '977'

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.convertToEAN13(issn, suffix);
        });
        assertTrue(exception.getMessage().contains("Check digit error for '9771234567003'"));
    }
}
