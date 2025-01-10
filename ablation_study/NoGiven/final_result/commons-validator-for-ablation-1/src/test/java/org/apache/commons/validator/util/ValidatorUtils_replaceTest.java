
package org.apache.commons.validator.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorUtils_replaceTest {

    @Test
    void testReplace_AllNonNull() {
        String result = ValidatorUtils.replace("Hello World", "World", "Universe");
        assertEquals("Hello Universe", result);
    }

    @Test
    void testReplace_AnyNull() {
        String result = ValidatorUtils.replace(null, "key", "value");
        assertNull(result);

        result = ValidatorUtils.replace("Hello World", null, "value");
        assertEquals("Hello World", result);

        result = ValidatorUtils.replace("Hello World", "key", null);
        assertEquals("Hello World", result);
    }
}
