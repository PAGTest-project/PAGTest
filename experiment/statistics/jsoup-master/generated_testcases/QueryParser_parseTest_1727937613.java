
package org.jsoup.select;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QueryParser_parseTest {

    @Test
    void testParse_ValidQuery() {
        String query = "div.class";
        Evaluator evaluator = QueryParser.parse(query);
        assertNotNull(evaluator);
    }

    @Test
    void testParse_InvalidQuery() {
        String query = "invalidQuery";
        assertThrows(IllegalArgumentException.class, () -> {
            QueryParser.parse(query);
        });
    }
}
