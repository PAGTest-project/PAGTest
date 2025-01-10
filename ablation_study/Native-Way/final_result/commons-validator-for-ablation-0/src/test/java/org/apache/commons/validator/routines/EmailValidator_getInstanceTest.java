
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailValidator_getInstanceTest {

    @Test
    public void testGetInstanceAllowLocalAndTld() {
        EmailValidator validator = EmailValidator.getInstance(true, true);
        assertNotNull(validator);
    }

    @Test
    public void testGetInstanceAllowLocalOnly() {
        EmailValidator validator = EmailValidator.getInstance(true, false);
        assertNotNull(validator);
    }

    @Test
    public void testGetInstanceAllowTldOnly() {
        EmailValidator validator = EmailValidator.getInstance(false, true);
        assertNotNull(validator);
    }

    @Test
    public void testGetInstanceDefault() {
        EmailValidator validator = EmailValidator.getInstance(false, false);
        assertNotNull(validator);
    }
}
