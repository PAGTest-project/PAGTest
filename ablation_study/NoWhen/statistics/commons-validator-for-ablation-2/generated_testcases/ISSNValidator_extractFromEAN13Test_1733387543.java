
package org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISSNValidator_extractFromEAN13Test {

    private ISSNValidator issnValidator;

    @BeforeEach
    public void setUp() {
        issnValidator = new ISSNValidator();
    }

    @Test
    public void testExtractFromEAN13Valid() {
        String ean13 = "9771234567002";
        String expectedISSN = "12345679";
        assertEquals(expectedISSN, issnValidator.extractFromEAN13(ean13));
    }

    @Test
    public void testExtractFromEAN13InvalidLength() {
        String ean13 = "977123456700";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.extractFromEAN13(ean13);
        });
        assertEquals("Invalid length 12 for '977123456700'", exception.getMessage());
    }

    @Test
    public void testExtractFromEAN13InvalidPrefix() {
        String ean13 = "9781234567002";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.extractFromEAN13(ean13);
        });
        assertEquals("Prefix must be 977 to contain an ISSN: '9781234567002'", exception.getMessage());
    }

    @Test
    public void testExtractFromEAN13NullResult() {
        String ean13 = "9771234567003"; // Assuming this EAN-13 is invalid
        assertNull(issnValidator.extractFromEAN13(ean13));
    }

    @Test
    public void testExtractFromEAN13CheckDigitException() {
        String ean13 = "9771234567004"; // Assuming this EAN-13 causes a CheckDigitException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.extractFromEAN13(ean13);
        });
        assertTrue(exception.getMessage().contains("Check digit error for '9771234567004'"));
    }
}
