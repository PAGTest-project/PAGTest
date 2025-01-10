
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentFormatterUtil_unmaskTest {

    @Test
    public void testUnmask() {
        // Given
        String input = "12.345.678/0001-90";
        String expected = "12345678000190";

        // When
        String result = DocumentFormatterUtil.unmask(input);

        // Then
        assertEquals(expected, result);
    }
}
