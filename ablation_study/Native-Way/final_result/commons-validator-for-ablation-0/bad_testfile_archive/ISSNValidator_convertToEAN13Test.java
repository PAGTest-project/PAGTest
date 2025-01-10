
package org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
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
        String expectedEAN13 = "97712345670009";
        assertEquals(expectedEAN13, validator.convertToEAN13(issn, suffix));
    }

    @Test
    public void testConvertToEAN13InvalidSuffix() {
        String issn = "1234-5679";
        String suffix = "AB";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.convertToEAN13(issn, suffix);
        });
        assertEquals("Suffix must be two digits: 'AB'", exception.getMessage());
    }

    @Test
    public void testConvertToEAN13NullSuffix() {
        String issn = "1234-5679";
        String suffix = null;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.convertToEAN13(issn, suffix);
        });
        assertEquals("Suffix must be two digits: 'null'", exception.getMessage());
    }

    @Test
    public void testConvertToEAN13InvalidISSN() {
        String issn = "1234-5678"; // Invalid ISSN
        String suffix = "00";
        assertNull(validator.convertToEAN13(issn, suffix));
    }

    @Test
    public void testConvertToEAN13CheckDigitException() {
        String issn = "1234-5679";
        String suffix = "00";
        // Mocking a CheckDigitException scenario
        ISSNValidator mockValidator = new ISSNValidator() {
            @Override
            public Object validate(final String code) {
                return "1234567"; // Valid ISSN without check digit
            }
        };
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            mockValidator.convertToEAN13(issn, suffix);
        });
        assertTrue(exception.getMessage().contains("Check digit error for '9771234567000'"));
    }
}
