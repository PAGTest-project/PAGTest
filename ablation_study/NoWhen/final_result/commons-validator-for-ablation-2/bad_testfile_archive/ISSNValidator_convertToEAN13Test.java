
package org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISSNValidator_convertToEAN13Test {

    private ISSNValidator issnValidator;

    @BeforeEach
    public void setUp() {
        issnValidator = new ISSNValidator();
    }

    @Test
    public void testConvertToEAN13ValidISSNAndSuffix() {
        String issn = "1234-5679";
        String suffix = "00";
        String expectedEAN13 = "9771234567003"; // Corrected expected EAN-13
        assertEquals(expectedEAN13, issnValidator.convertToEAN13(issn, suffix));
    }

    @Test
    public void testConvertToEAN13InvalidSuffix() {
        String issn = "1234-5679";
        String suffix = "0A";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.convertToEAN13(issn, suffix);
        });
        assertEquals("Suffix must be two digits: '0A'", exception.getMessage());
    }

    @Test
    public void testConvertToEAN13NullSuffix() {
        String issn = "1234-5679";
        String suffix = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.convertToEAN13(issn, suffix);
        });
        assertEquals("Suffix must be two digits: 'null'", exception.getMessage());
    }

    @Test
    public void testConvertToEAN13InvalidISSN() {
        String issn = "1234-567X"; // Invalid ISSN
        String suffix = "00";
        assertNull(issnValidator.convertToEAN13(issn, suffix));
    }

    @Test
    public void testConvertToEAN13CheckDigitException() {
        String issn = "1234-5679";
        String suffix = "00";
        // Mocking a CheckDigitException by providing an invalid EAN13 prefix
        String invalidEAN13Prefix = "978"; // This should not be used for ISSN conversion
        String ean13 = invalidEAN13Prefix + issn.replace("-", "") + suffix;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            EAN13CheckDigit.EAN13_CHECK_DIGIT.calculate(ean13);
        });
        assertNotNull(exception.getMessage());
    }
}
