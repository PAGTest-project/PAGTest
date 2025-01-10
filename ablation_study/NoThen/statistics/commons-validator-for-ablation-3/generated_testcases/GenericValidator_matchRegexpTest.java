
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericValidator_matchRegexpTest {

    @Test
    public void testMatchRegexp_NullRegexp() {
        assertFalse(GenericValidator.matchRegexp("testValue", null));
    }

    @Test
    public void testMatchRegexp_EmptyRegexp() {
        assertFalse(GenericValidator.matchRegexp("testValue", ""));
    }

    @Test
    public void testMatchRegexp_ValidMatch() {
        assertTrue(GenericValidator.matchRegexp("testValue", "test.*"));
    }

    @Test
    public void testMatchRegexp_InvalidMatch() {
        assertFalse(GenericValidator.matchRegexp("testValue", "invalid.*"));
    }
}
