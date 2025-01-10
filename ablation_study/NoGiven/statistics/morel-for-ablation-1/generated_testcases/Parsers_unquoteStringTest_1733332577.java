
package net.hydromatic.morel.parse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parsers_unquoteStringTest {

    @Test
    public void testUnquoteString() {
        String input = "\"\\\"Hello\\\\World\\\"\"";
        String expected = "\"Hello\\World\"";
        String result = Parsers.unquoteString(input);
        assertEquals(expected, result);
    }
}
