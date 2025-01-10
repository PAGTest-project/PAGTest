
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericValidator_matchRegexpTest {

    @Test
    public void testMatchRegexp_ValidPattern() {
        assertTrue(GenericValidator.matchRegexp("abc123", "^[a-z0-9]+$"));
    }

    @Test
    public void testMatchRegexp_InvalidPattern() {
        assertFalse(GenericValidator.matchRegexp("abc123", "^[A-Z0-9]+$"));
    }

    @Test
    public void testMatchRegexp_NullPattern() {
        assertFalse(GenericValidator.matchRegexp("abc123", null));
    }

    @Test
    public void testMatchRegexp_EmptyPattern() {
        assertFalse(GenericValidator.matchRegexp("abc123", ""));
    }
}
