
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_isValidTest {

    private static final String REGEX_1 = "^[a-z]+$";
    private static final String REGEX_2 = "^[A-Z]+$";
    private static final String REGEX_3 = "^[0-9]+$";
    private static final String[] MULTIPLE_REGEX = {REGEX_1, REGEX_2, REGEX_3};

    private RegexValidator multipleRegexValidator;
    private RegexValidator singleRegexValidator1;
    private RegexValidator singleRegexValidator2;
    private RegexValidator singleRegexValidator3;

    @BeforeEach
    public void setUp() {
        multipleRegexValidator = new RegexValidator(MULTIPLE_REGEX);
        singleRegexValidator1 = new RegexValidator(REGEX_1);
        singleRegexValidator2 = new RegexValidator(REGEX_2);
        singleRegexValidator3 = new RegexValidator(REGEX_3);
    }

    @Test
    public void testIsValidWithNullValue() {
        assertFalse(multipleRegexValidator.isValid(null));
    }

    @Test
    public void testIsValidWithMatchingValue() {
        assertTrue(multipleRegexValidator.isValid("abc"));
        assertTrue(multipleRegexValidator.isValid("ABC"));
        assertTrue(multipleRegexValidator.isValid("123"));
    }

    @Test
    public void testIsValidWithNonMatchingValue() {
        assertFalse(multipleRegexValidator.isValid("abc123"));
        assertFalse(multipleRegexValidator.isValid("ABC123"));
        assertFalse(multipleRegexValidator.isValid("abcABC"));
    }

    @Test
    public void testIsValidWithSingleRegexValidators() {
        assertTrue(singleRegexValidator1.isValid("abc"));
        assertFalse(singleRegexValidator1.isValid("ABC"));
        assertFalse(singleRegexValidator1.isValid("123"));

        assertFalse(singleRegexValidator2.isValid("abc"));
        assertTrue(singleRegexValidator2.isValid("ABC"));
        assertFalse(singleRegexValidator2.isValid("123"));

        assertFalse(singleRegexValidator3.isValid("abc"));
        assertFalse(singleRegexValidator3.isValid("ABC"));
        assertTrue(singleRegexValidator3.isValid("123"));
    }

    @Test
    public void testValidateWithNullValue() {
        assertNull(multipleRegexValidator.validate(null));
    }

    @Test
    public void testValidateWithMatchingValue() {
        assertEquals("abc", multipleRegexValidator.validate("abc"));
        assertEquals("ABC", multipleRegexValidator.validate("ABC"));
        assertEquals("123", multipleRegexValidator.validate("123"));
    }

    @Test
    public void testValidateWithNonMatchingValue() {
        assertNull(multipleRegexValidator.validate("abc123"));
        assertNull(multipleRegexValidator.validate("ABC123"));
        assertNull(multipleRegexValidator.validate("abcABC"));
    }

    @Test
    public void testMatchWithNullValue() {
        assertNull(multipleRegexValidator.match(null));
    }

    @Test
    public void testMatchWithMatchingValue() {
        assertArrayEquals(new String[]{"abc"}, multipleRegexValidator.match("abc"));
        assertArrayEquals(new String[]{"ABC"}, multipleRegexValidator.match("ABC"));
        assertArrayEquals(new String[]{"123"}, multipleRegexValidator.match("123"));
    }

    @Test
    public void testMatchWithNonMatchingValue() {
        assertNull(multipleRegexValidator.match("abc123"));
        assertNull(multipleRegexValidator.match("ABC123"));
        assertNull(multipleRegexValidator.match("abcABC"));
    }

    private void checkArray(String message, String[] expected, String[] actual) {
        if (expected == null) {
            assertNull(actual, message);
        } else {
            assertArrayEquals(expected, actual, message);
        }
    }
}
