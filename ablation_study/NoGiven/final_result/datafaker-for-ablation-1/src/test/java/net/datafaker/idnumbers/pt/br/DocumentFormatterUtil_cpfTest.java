
package net.datafaker.idnumbers.pt.br;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DocumentFormatterUtil_cpfTest {

    @Test
    public void testCpfFormatting() {
        String input = "12345678909";
        String expected = "123.456.789-09";
        String result = DocumentFormatterUtil.cpf(input);
        assertEquals(expected, result);
    }
}
