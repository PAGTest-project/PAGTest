
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISINValidator_validateTest {

    private static final ISINValidator VALIDATOR_TRUE = ISINValidator.getInstance(true);
    private static final ISINValidator VALIDATOR_FALSE = ISINValidator.getInstance(false);

    private static final String[] invalidFormat = {
        "US0123456789", // Invalid length
        "US012345678A", // Invalid character
        "US01234567890", // Invalid check digit
    };

    private static final String[] invalidFormatTrue = {
        "ZZ0123456789", // Invalid country code
    };

    private static final String[] validFormat = {
        "US0123456789", // Valid ISIN
    };

    @BeforeEach
    public void setUp() {
        // Any setup code if needed
    }

    @Test
    public void testValidateValidISIN() {
        for (final String v : validFormat) {
            assertNotNull(VALIDATOR_TRUE.validate(v), v);
        }
    }

    @Test
    public void testValidateInvalidISIN() {
        for (final String f : invalidFormat) {
            assertNull(VALIDATOR_TRUE.validate(f), f);
        }
    }

    @Test
    public void testValidateInvalidCountryCode() {
        for (final String f : invalidFormatTrue) {
            assertNull(VALIDATOR_TRUE.validate(f), f);
        }
    }

    @Test
    public void testValidateNoCountryCodeCheck() {
        for (final String v : validFormat) {
            assertNotNull(VALIDATOR_FALSE.validate(v), v);
        }
        for (final String f : invalidFormatTrue) {
            assertNull(VALIDATOR_FALSE.validate(f), f);
        }
    }
}
