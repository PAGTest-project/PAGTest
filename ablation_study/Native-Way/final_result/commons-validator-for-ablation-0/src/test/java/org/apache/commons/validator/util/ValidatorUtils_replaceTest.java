
package org.apache.commons.validator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ValidatorUtils_replaceTest {

    @Test
    public void testReplace_AllNonNull() {
        String value = "Hello, World!";
        String key = "World";
        String replaceValue = "Universe";
        assertEquals("Hello, Universe!", ValidatorUtils.replace(value, key, replaceValue));
    }

    @Test
    public void testReplace_ValueIsNull() {
        String value = null;
        String key = "World";
        String replaceValue = "Universe";
        assertEquals(null, ValidatorUtils.replace(value, key, replaceValue));
    }

    @Test
    public void testReplace_KeyIsNull() {
        String value = "Hello, World!";
        String key = null;
        String replaceValue = "Universe";
        assertEquals("Hello, World!", ValidatorUtils.replace(value, key, replaceValue));
    }

    @Test
    public void testReplace_ReplaceValueIsNull() {
        String value = "Hello, World!";
        String key = "World";
        String replaceValue = null;
        assertEquals("Hello, World!", ValidatorUtils.replace(value, key, replaceValue));
    }

    @Test
    public void testReplace_KeyNotFound() {
        String value = "Hello, World!";
        String key = "Earth";
        String replaceValue = "Universe";
        assertEquals("Hello, World!", ValidatorUtils.replace(value, key, replaceValue));
    }
}
