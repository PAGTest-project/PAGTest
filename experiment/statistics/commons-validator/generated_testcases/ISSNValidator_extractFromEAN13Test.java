
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
        String ean13 = "9771234567000";
        String expectedISSN = "12345670";
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
        String ean13 = "9781234567000";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.extractFromEAN13(ean13);
        });
        assertEquals("Prefix must be 977 to contain an ISSN: '9781234567000'", exception.getMessage());
    }

    @Test
    public void testExtractFromEAN13InvalidEAN() {
        String ean13 = "9771234567001";
        assertNull(issnValidator.extractFromEAN13(ean13));
    }

    @Test
    public void testExtractFromEAN13CheckDigitException() {
        String ean13 = "9771234567000";
        // Simulate a CheckDigitException by modifying the input to cause an error
        String invalidEAN13 = ean13.substring(0, 12) + "X";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            issnValidator.extractFromEAN13(invalidEAN13);
        });
        assertTrue(exception.getMessage().contains("Check digit error for '977123456700X'"));
    }
}
