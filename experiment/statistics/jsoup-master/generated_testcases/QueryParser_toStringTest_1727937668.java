
package org.jsoup.select;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QueryParser_toStringTest {

    @Test
    public void testToString() {
        QueryParser parser = new QueryParser("div.class");
        assertEquals("div.class", parser.toString());
    }
}
