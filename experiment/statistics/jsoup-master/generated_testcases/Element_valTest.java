
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.jsoup.parser.Parser.NamespaceHtml;

public class Element_valTest {

    @Test
    public void testVal_textarea() {
        Element element = new Element("textarea");
        element.text("sampleText");
        assertEquals("sampleText", element.val());
    }

    @Test
    public void testVal_nonTextarea() {
        Element element = new Element("input");
        element.attr("value", "sampleValue");
        assertEquals("sampleValue", element.val());
    }
}
