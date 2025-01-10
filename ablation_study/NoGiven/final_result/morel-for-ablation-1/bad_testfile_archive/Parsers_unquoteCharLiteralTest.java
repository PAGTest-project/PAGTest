
package net.hydromatic.morel.parse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Parsers_unquoteCharLiteralTest {

    @Test
    void testUnquoteCharLiteral_ValidInput() {
        char result = Parsers.unquoteCharLiteral("#\"a\"");
        assertEquals('a', result);
    }

    @Test
    void testUnquoteCharLiteral_InvalidLength() {
        assertThrows(RuntimeException.class, () -> {
            Parsers.unquoteCharLiteral("#\"ab\"");
        });
    }
}
