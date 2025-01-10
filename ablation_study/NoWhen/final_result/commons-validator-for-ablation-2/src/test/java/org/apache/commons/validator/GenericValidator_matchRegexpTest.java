
package org.apache.commons.validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenericValidator_matchRegexpTest {

    @Test
    public void testMatchRegexp_NullRegexp() {
        assertFalse(GenericValidator.matchRegexp("test", null));
    }

    @Test
    public void testMatchRegexp_EmptyRegexp() {
        assertFalse(GenericValidator.matchRegexp("test", ""));
    }

    @Test
    public void testMatchRegexp_ValidMatch() {
        assertTrue(GenericValidator.matchRegexp("test", "test"));
    }

    @Test
    public void testMatchRegexp_InvalidMatch() {
        assertFalse(GenericValidator.matchRegexp("test", "nottest"));
    }
}
