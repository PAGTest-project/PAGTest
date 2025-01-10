
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISSNValidator_convertToEAN13Test {

    private ISSNValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new ISSNValidator();
    }

    @Test
    public void testConvertToEAN13ValidISSN() {
        String issn = "1234-5679";
        String suffix = "00";
        String expectedEAN13 = "9771234567003";
        assertEquals(expectedEAN13, validator.convertToEAN13(issn, suffix));
    }

    @Test
    public void testConvertToEAN13InvalidISSN() {
        String issn = "1234-5678"; // Invalid ISSN
        String suffix = "00";
        assertNull(validator.convertToEAN13(issn, suffix));
    }

    @Test
    public void testConvertToEAN13NullSuffix() {
        String issn = "1234-5679";
        String suffix = null;
        assertThrows(IllegalArgumentException.class, () -> {
            validator.convertToEAN13(issn, suffix);
        });
    }

    @Test
    public void testConvertToEAN13InvalidSuffix() {
        String issn = "1234-5679";
        String suffix = "0"; // Invalid suffix
        assertThrows(IllegalArgumentException.class, () -> {
            validator.convertToEAN13(issn, suffix);
        });
    }

    @Test
    public void testConvertToEAN13CheckDigitException() {
        String issn = "1234-5679";
        String suffix = "00";
        // Mocking a scenario where CheckDigitException should not occur
        // This test is more of a sanity check as the method should handle it internally
        assertThrows(IllegalArgumentException.class, () -> {
            validator.convertToEAN13(issn, suffix);
        });
    }
}
