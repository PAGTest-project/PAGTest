
package org.apache.commons.validator.routines;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegexValidator_validateTest {

    private static final String[] MULTIPLE_REGEX = {
        "^([A-Z]{2})$", // Matches two uppercase letters
        "^([0-9]{3})$", // Matches three digits
        "^([a-z]{4})$"  // Matches four lowercase letters
    };

    private RegexValidator regexValidator;

    @BeforeEach
    public void setUp() {
        regexValidator = new RegexValidator(MULTIPLE_REGEX);
    }

    @Test
    public void testValidateNullValue() {
        assertNull(regexValidator.validate(null));
    }

    @Test
    public void testValidateSingleGroupMatch() {
        assertEquals("AB", regexValidator.validate("AB"));
    }

    @Test
    public void testValidateMultipleGroupMatch() {
        assertEquals("123", regexValidator.validate("123"));
    }

    @Test
    public void testValidateNoMatch() {
        assertNull(regexValidator.validate("XYZ"));
    }

    @Test
    public void testValidateMultipleGroupsConcatenation() {
        assertEquals("abcd", regexValidator.validate("abcd"));
    }

    @Test
    public void testValidateWithIsValid() {
        String value = "AB";
        assertTrue(regexValidator.isValid(value));
        assertEquals("AB", regexValidator.validate(value));
    }

    @Test
    public void testValidateWithMatch() {
        String value = "123";
        String[] matchedGroups = regexValidator.match(value);
        assertNotNull(matchedGroups);
        assertEquals(1, matchedGroups.length);
        assertEquals("123", matchedGroups[0]);
        assertEquals("123", regexValidator.validate(value));
    }

    @Test
    public void testValidateToString() {
        String value = "abcd";
        regexValidator.validate(value);
        String validatorString = regexValidator.toString();
        assertTrue(validatorString.contains("^([A-Z]{2})$"));
        assertTrue(validatorString.contains("^([0-9]{3})$"));
        assertTrue(validatorString.contains("^([a-z]{4})$"));
    }

    @Test
    public void testGetPatterns() {
        assertNotSame(regexValidator.getPatterns(), regexValidator.getPatterns());
        Pattern[] patterns = regexValidator.getPatterns();
        assertEquals("^([A-Z]{2})$", patterns[0].pattern());
        assertEquals("^([0-9]{3})$", patterns[1].pattern());
        assertEquals("^([a-z]{4})$", patterns[2].pattern());
    }
}
