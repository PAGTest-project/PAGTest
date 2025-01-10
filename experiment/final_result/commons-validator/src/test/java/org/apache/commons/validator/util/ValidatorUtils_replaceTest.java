
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ValidatorUtils_replaceTest {

    @Test
    public void testReplace_AllNonNull() {
        String value = "Hello, world!";
        String key = "world";
        String replaceValue = "Java";
        assertEquals("Hello, Java!", ValidatorUtils.replace(value, key, replaceValue));
    }

    @Test
    public void testReplace_ValueNull() {
        String value = null;
        String key = "world";
        String replaceValue = "Java";
        assertEquals(null, ValidatorUtils.replace(value, key, replaceValue));
    }

    @Test
    public void testReplace_KeyNull() {
        String value = "Hello, world!";
        String key = null;
        String replaceValue = "Java";
        assertEquals("Hello, world!", ValidatorUtils.replace(value, key, replaceValue));
    }

    @Test
    public void testReplace_ReplaceValueNull() {
        String value = "Hello, world!";
        String key = "world";
        String replaceValue = null;
        assertEquals("Hello, world!", ValidatorUtils.replace(value, key, replaceValue));
    }
}
