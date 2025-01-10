
package net.hydromatic.morel.parse;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Parsers_appendIdTest {

    @Test
    public void testAppendIdWithBacktick() {
        StringBuilder buf = new StringBuilder();
        String id = "id`with`backtick";
        StringBuilder result = Parsers.appendId(buf, id);
        assertEquals("`id``with``backtick`", result.toString());
    }

    @Test
    public void testAppendIdWithoutBacktick() {
        StringBuilder buf = new StringBuilder();
        String id = "id_without_backtick";
        StringBuilder result = Parsers.appendId(buf, id);
        assertEquals("id_without_backtick", result.toString());
    }
}
