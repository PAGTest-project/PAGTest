
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ISINValidator_validateTest {

    private static final ISINValidator VALIDATOR_FALSE = ISINValidator.getInstance(false);
    private static final ISINValidator VALIDATOR_TRUE = ISINValidator.getInstance(true);

    private static final String[] validFormat = {
        "US0378331005", "AU0000XVGZA3", "GB0002634946"
    };

    private static final String[] invalidFormat = {
        "US037833100", "AU0000XVGZA", "GB000263494"
    };

    private static final String[] invalidCountryCode = {
        "ZZ0378331005", "XX0000XVGZA3", "YY0002634946"
    };

    @Test
    public void testValidateValidFormat() {
        for (final String f : validFormat) {
            assertNotNull(VALIDATOR_FALSE.validate(f), f);
        }
    }

    @Test
    public void testValidateInvalidFormat() {
        for (final String f : invalidFormat) {
            assertNull(VALIDATOR_FALSE.validate(f), f);
        }
    }

    @Test
    public void testValidateValidCountryCode() {
        for (final String f : validFormat) {
            assertNotNull(VALIDATOR_TRUE.validate(f), f);
        }
    }

    @Test
    public void testValidateInvalidCountryCode() {
        for (final String f : invalidCountryCode) {
            assertNull(VALIDATOR_TRUE.validate(f), f);
        }
    }
}
