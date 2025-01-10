
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentFormatterUtil_cnpjTest {

    @Test
    public void testCnpjFormatting() {
        // Given
        String input = "12345678000190";
        String expected = "12.345.678/0001-90";

        // When
        String result = DocumentFormatterUtil.cnpj(input);

        // Then
        assertEquals(expected, result);
    }
}
