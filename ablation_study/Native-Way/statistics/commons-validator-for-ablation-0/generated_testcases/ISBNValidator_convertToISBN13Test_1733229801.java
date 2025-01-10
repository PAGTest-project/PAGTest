
package org.apache.commons.validator.routines;

import org.apache.commons.validator.routines.checkdigit.CheckDigitException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ISBNValidator_convertToISBN13Test {

    private ISBNValidator isbnValidator;

    @BeforeEach
    public void setUp() {
        isbnValidator = new ISBNValidator();
    }

    @Test
    public void testConvertToISBN13_NullInput() {
        assertNull(isbnValidator.convertToISBN13(null));
    }

    @Test
    public void testConvertToISBN13_InvalidLength() {
        assertThrows(IllegalArgumentException.class, () -> {
            isbnValidator.convertToISBN13("123456789");
        });
    }

    @Test
    public void testConvertToISBN13_ValidInput() {
        String isbn13 = isbnValidator.convertToISBN13("1234567890");
        assertNotNull(isbn13);
        assertEquals(13, isbn13.length());
        assertTrue(isbn13.startsWith("978"));
    }

    @Test
    public void testConvertToISBN13_CheckDigitException() {
        // Assuming the calculate method throws a CheckDigitException for this input
        assertThrows(CheckDigitException.class, () -> {
            isbnValidator.convertToISBN13("123456789X");
        });
    }
}
