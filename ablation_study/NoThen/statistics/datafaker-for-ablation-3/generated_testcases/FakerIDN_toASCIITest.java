
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakerIDN_toASCIITest {

    @Test
    public void testToASCII_SuccessfulConversion() {
        String input = "test";
        String expected = "test";
        String result = FakerIDN.toASCII(input);
        assertEquals(expected, result);
    }

    @Test
    public void testToASCII_CharacterByCharacterConversion() {
        String input = "тест";
        String expected = "xn--e1aybc";
        String result = FakerIDN.toASCII(input);
        assertEquals(expected, result);
    }

    @Test
    public void testToASCII_EmptyResultThrowsException() {
        String input = "أبجد";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> FakerIDN.toASCII(input));
        assertEquals("Unable to convert \"أبجد\" to ASCII", exception.getMessage());
    }
}
