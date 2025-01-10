
package org.apache.commons.validator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorUtils_replaceTest {

    @Test
    void testReplace_AllNonNull() {
        String result = ValidatorUtils.replace("abc", "b", "d");
        assertEquals("adc", result);
    }

    @Test
    void testReplace_ValueNull() {
        String result = ValidatorUtils.replace(null, "b", "d");
        assertNull(result);
    }

    @Test
    void testReplace_KeyNull() {
        String result = ValidatorUtils.replace("abc", null, "d");
        assertEquals("abc", result);
    }

    @Test
    void testReplace_ReplaceValueNull() {
        String result = ValidatorUtils.replace("abc", "b", null);
        assertEquals("abc", result);
    }
}
