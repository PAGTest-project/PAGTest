
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailValidator_getInstanceTest {

    @Test
    public void testGetInstanceAllowLocalAndTld() {
        EmailValidator validator = EmailValidator.getInstance(true, true);
        assertNotNull(validator);
        assertTrue(validator == EmailValidator.EMAIL_VALIDATOR_WITH_LOCAL_WITH_TLD);
    }

    @Test
    public void testGetInstanceAllowLocalOnly() {
        EmailValidator validator = EmailValidator.getInstance(true, false);
        assertNotNull(validator);
        assertTrue(validator == EmailValidator.EMAIL_VALIDATOR_WITH_LOCAL);
    }

    @Test
    public void testGetInstanceAllowTldOnly() {
        EmailValidator validator = EmailValidator.getInstance(false, true);
        assertNotNull(validator);
        assertTrue(validator == EmailValidator.EMAIL_VALIDATOR_WITH_TLD);
    }

    @Test
    public void testGetInstanceDefault() {
        EmailValidator validator = EmailValidator.getInstance(false, false);
        assertNotNull(validator);
        assertTrue(validator == EmailValidator.EMAIL_VALIDATOR);
    }
}
