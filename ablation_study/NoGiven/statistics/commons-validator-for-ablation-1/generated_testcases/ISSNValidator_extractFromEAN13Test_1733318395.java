
package org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISSNValidator_extractFromEAN13Test {

    private ISSNValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new ISSNValidator();
    }

    @Test
    public void testExtractFromEAN13Valid() {
        String ean13 = "9771234567000";
        String expectedISSN = "12345670";
        assertEquals(expectedISSN, validator.extractFromEAN13(ean13));
    }

    @Test
    public void testExtractFromEAN13InvalidLength() {
        String ean13 = "977123456700";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.extractFromEAN13(ean13);
        });
        assertTrue(exception.getMessage().contains("Invalid length"));
    }

    @Test
    public void testExtractFromEAN13InvalidPrefix() {
        String ean13 = "9781234567000";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.extractFromEAN13(ean13);
        });
        assertTrue(exception.getMessage().contains("Prefix must be 977"));
    }

    @Test
    public void testExtractFromEAN13NullResult() {
        String ean13 = "9771234567001"; // Assuming this is an invalid EAN-13 code
        assertNull(validator.extractFromEAN13(ean13));
    }

    @Test
    public void testExtractFromEAN13CheckDigitException() {
        String ean13 = "9771234567002"; // Assuming this triggers a CheckDigitException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.extractFromEAN13(ean13);
        });
        assertTrue(exception.getMessage().contains("Check digit error"));
    }
}
