
package net.hydromatic.morel.parse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parsers_unquoteIdentifierTest {

    @Test
    public void testUnquoteIdentifier() {
        String input = "`abc``def`";
        String expected = "abc`def";
        String result = Parsers.unquoteIdentifier(input);
        assertEquals(expected, result);
    }
}
