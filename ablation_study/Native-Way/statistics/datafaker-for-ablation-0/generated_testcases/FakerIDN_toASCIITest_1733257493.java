
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;
import java.net.IDN;
import static org.junit.jupiter.api.Assertions.*;

public class FakerIDN_toASCIITest {

    @Test
    public void testToASCII_SuccessfulConversion() {
        String input = "test";
        String expected = IDN.toASCII(input);
        assertEquals(expected, FakerIDN.toASCII(input));
    }

    @Test
    public void testToASCII_CharacterByCharacterConversion() {
        String input = "test";
        String expected = "test";
        assertEquals(expected, FakerIDN.toASCII(input));
    }

    @Test
    public void testToASCII_EmptyResultThrowsException() {
        String input = "";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> FakerIDN.toASCII(input));
        assertEquals("Unable to convert \"\" to ASCII", exception.getMessage());
    }
}
