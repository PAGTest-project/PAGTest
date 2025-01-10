
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISINValidator_validateTest {

    private static final ISINValidator VALIDATOR_FALSE = ISINValidator.getInstance(false);
    private static final ISINValidator VALIDATOR_TRUE = ISINValidator.getInstance(true);

    private final String[] validFormat = {
        "US0378331005", // Valid ISIN with valid country code
        "AU0000XVGZA3", // Valid ISIN with valid country code
        "GB0002634946" // Valid ISIN with valid country code
    };

    private final String[] invalidFormat = {
        "US037833100", // Invalid length
        "US03783310055", // Invalid length
        "US037833100A", // Invalid check digit
        "XX0378331005" // Invalid country code
    };

    @BeforeEach
    public void setUp() {
        // No setup required as ISINValidator is stateless
    }

    @Test
    public void testValidateValidISIN() {
        for (final String f : validFormat) {
            assertEquals(f, VALIDATOR_FALSE.validate(f));
        }
    }

    @Test
    public void testValidateInvalidISIN() {
        for (final String f : invalidFormat) {
            assertNull(VALIDATOR_FALSE.validate(f));
        }
    }

    @Test
    public void testValidateValidISINWithCountryCheck() {
        for (final String f : validFormat) {
            assertEquals(f, VALIDATOR_TRUE.validate(f));
        }
    }

    @Test
    public void testValidateInvalidISINWithCountryCheck() {
        for (final String f : invalidFormat) {
            assertNull(VALIDATOR_TRUE.validate(f));
        }
    }

    @Test
    public void testValidateInvalidCountryCode() {
        final String invalidCountryCodeISIN = "XX0378331005";
        assertNull(VALIDATOR_TRUE.validate(invalidCountryCodeISIN));
    }
}
