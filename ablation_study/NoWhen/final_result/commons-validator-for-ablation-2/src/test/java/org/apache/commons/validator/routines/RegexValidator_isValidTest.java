
package org.apache.commons.validator.routines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        assertFalse(multipleRegexValidator.isValid(null), "isValid() with null value should return false");
    }

    @Test
    public void testIsValidWithMatchingValue() {
        String value = "abc";
        assertTrue(multipleRegexValidator.isValid(value), "isValid() with matching value should return true");
    }

    @Test
    public void testIsValidWithNonMatchingValue() {
        String value = "ABC123";
        assertFalse(multipleRegexValidator.isValid(value), "isValid() with non-matching value should return false");
    }

    @Test
    public void testIsValidWithSingleRegexMatching() {
        String value = "ABC";
        assertTrue(singleRegexValidator2.isValid(value), "isValid() with single regex matching should return true");
    }

    @Test
    public void testIsValidWithSingleRegexNonMatching() {
        String value = "abc";
        assertFalse(singleRegexValidator2.isValid(value), "isValid() with single regex non-matching should return false");
    }

    @Test
    public void testIsValidWithMultipleRegexMatching() {
        String value = "123";
        assertTrue(multipleRegexValidator.isValid(value), "isValid() with multiple regex matching should return true");
    }

    @Test
    public void testIsValidWithMultipleRegexNonMatching() {
        String value = "abcABC123";
        assertFalse(multipleRegexValidator.isValid(value), "isValid() with multiple regex non-matching should return false");
    }
}
