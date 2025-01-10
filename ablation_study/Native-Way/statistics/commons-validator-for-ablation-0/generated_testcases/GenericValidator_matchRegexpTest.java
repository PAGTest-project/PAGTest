
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericValidator_matchRegexpTest {

    @Test
    public void testMatchRegexp_ValidMatch() {
        assertTrue(GenericValidator.matchRegexp("abc123", "^[a-z0-9]+$"));
    }

    @Test
    public void testMatchRegexp_InvalidMatch() {
        assertFalse(GenericValidator.matchRegexp("abc123!", "^[a-z0-9]+$"));
    }

    @Test
    public void testMatchRegexp_NullRegexp() {
        assertFalse(GenericValidator.matchRegexp("abc123", null));
    }

    @Test
    public void testMatchRegexp_EmptyRegexp() {
        assertFalse(GenericValidator.matchRegexp("abc123", ""));
    }

    @Test
    public void testMatchRegexp_NullValue() {
        assertFalse(GenericValidator.matchRegexp(null, "^[a-z0-9]*$"));
    }

    @Test
    public void testMatchRegexp_EmptyValue() {
        assertFalse(GenericValidator.matchRegexp("", "^[a-z0-9]+$"));
    }
}
