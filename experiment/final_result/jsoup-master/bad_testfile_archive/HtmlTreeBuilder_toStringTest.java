
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.StringReader;

class HtmlTreeBuilder_toStringTest {

    @Test
    void testToString() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""), "baseUri", new Parser(Parser.htmlParser()));
        String expected = "TreeBuilder{currentToken=null, state=Initial, currentElement=null}";
        assertEquals(expected, builder.toString());
    }
}
