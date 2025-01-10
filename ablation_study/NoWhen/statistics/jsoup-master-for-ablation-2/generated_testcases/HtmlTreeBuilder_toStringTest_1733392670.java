
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HtmlTreeBuilder_toStringTest {

    @Test
    void testToString() {
        HtmlTreeBuilder builder = new HtmlTreeBuilder();
        builder.initialiseParse(new StringReader(""), "http://example.com", new Parser(Parser.htmlParser()));
        builder.initialiseParseFragment(new Element("div", "http://example.com"));

        String expected = "TreeBuilder{currentToken=null, state=Initial, currentElement=div}";
        assertEquals(expected, builder.toString());
    }
}
