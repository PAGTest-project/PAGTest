
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;

import java.net.IDN;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakerIDN_toASCIITest {

    @Test
    public void testToASCII_SuccessfulConversion() {
        String input = "test";
        String expected = IDN.toASCII(input);
        String actual = FakerIDN.toASCII(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testToASCII_CharacterByCharacterConversion() {
        String input = "fa.yml";
        String expected = "fayml"; // Assuming each character is converted individually
        String actual = FakerIDN.toASCII(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testToASCII_EmptyResultThrowsException() {
        String input = "א"; // A single character that cannot be converted
        assertThrows(RuntimeException.class, () -> FakerIDN.toASCII(input));
    }
}
