
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HtmlTreeBuilder_toStringTest {

    @Test
    void testToString() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""), "baseUri", null);
        String expected = "TreeBuilder{currentToken=null, state=Initial, currentElement=null}";
        assertEquals(expected, builder.toString());
    }
}
