
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
        String ean13 = "9771144875007";
        String expectedISSN = "1144875X";
        assertEquals(expectedISSN, issnValidator.extractFromEAN13(ean13));
    }

    @Test
    public void testExtractFromEAN13InvalidLength() {
        String ean13 = "977114487500";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.extractFromEAN13(ean13);
        });
        assertTrue(exception.getMessage().contains("Invalid length"));
    }

    @Test
    public void testExtractFromEAN13InvalidPrefix() {
        String ean13 = "9781144875007";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.extractFromEAN13(ean13);
        });
        assertTrue(exception.getMessage().contains("Prefix must be 977"));
    }

    @Test
    public void testExtractFromEAN13NullResult() {
        String ean13 = "9771144875008"; // Invalid check digit
        assertNull(issnValidator.extractFromEAN13(ean13));
    }

    @Test
    public void testExtractFromEAN13CheckDigitException() {
        String ean13 = "9771144875007";
        // Mocking a CheckDigitException by modifying the input to cause an exception
        String invalidEAN13 = ean13.substring(0, 12) + "A";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.extractFromEAN13(invalidEAN13);
        });
        assertTrue(exception.getMessage().contains("Check digit error"));
    }
}
