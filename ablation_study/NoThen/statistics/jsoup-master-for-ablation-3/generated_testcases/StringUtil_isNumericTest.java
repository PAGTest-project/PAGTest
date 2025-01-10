
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_isNumericTest {

    @Test
    void testIsNumericWithNullString() {
        assertFalse(StringUtil.isNumeric(null));
    }

    @Test
    void testIsNumericWithEmptyString() {
        assertFalse(StringUtil.isNumeric(""));
    }

    @Test
    void testIsNumericWithNonNumericString() {
        assertFalse(StringUtil.isNumeric("abc123"));
    }

    @Test
    void testIsNumericWithNumericString() {
        assertTrue(StringUtil.isNumeric("123456"));
    }

    @Test
    void testIsNumericWithWhitespaceString() {
        assertFalse(StringUtil.isNumeric(" 123 "));
    }

    @Test
    void testIsNumericWithSpecialCharacters() {
        assertFalse(StringUtil.isNumeric("123.45"));
    }

    @Test
    void testIsNumericWithSingleDigit() {
        assertTrue(StringUtil.isNumeric("5"));
    }

    @Test
    void testIsNumericWithLeadingZeros() {
        assertTrue(StringUtil.isNumeric("000123"));
    }

    @Test
    void testIsNumericWithTrailingZeros() {
        assertTrue(StringUtil.isNumeric("123000"));
    }

    @Test
    void testIsNumericWithMixedDigitsAndLetters() {
        assertFalse(StringUtil.isNumeric("123a456"));
    }
}
