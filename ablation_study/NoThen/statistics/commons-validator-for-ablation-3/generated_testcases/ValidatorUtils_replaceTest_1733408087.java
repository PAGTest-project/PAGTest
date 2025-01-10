
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ValidatorUtils_replaceTest {

    @Test
    public void testReplace_AllNonNull() {
        String value = "Hello World";
        String key = "World";
        String replaceValue = "Universe";
        String expected = "Hello Universe";
        String result = ValidatorUtils.replace(value, key, replaceValue);
        assertEquals(expected, result);
    }

    @Test
    public void testReplace_AnyNull() {
        String value = "Hello World";
        String key = null;
        String replaceValue = "Universe";
        String expected = "Hello World";
        String result = ValidatorUtils.replace(value, key, replaceValue);
        assertEquals(expected, result);
    }
}
