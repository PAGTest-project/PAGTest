
package net.datafaker.internal.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FakerIDN_toASCIITest {

    @Test
    public void testToASCII_SuccessfulConversion() {
        String input = "test";
        String expected = "test";
        String result = FakeValuesService.toASCII(input);
        assertEquals(expected, result);
    }

    @Test
    public void testToASCII_CharacterByCharacterConversion() {
        String input = "tést";
        String expected = "test";
        String result = FakeValuesService.toASCII(input);
        assertEquals(expected, result);
    }

    @Test
    public void testToASCII_EmptyResultThrowsException() {
        String input = "ééé";
        assertThrows(RuntimeException.class, () -> FakeValuesService.toASCII(input));
    }
}
