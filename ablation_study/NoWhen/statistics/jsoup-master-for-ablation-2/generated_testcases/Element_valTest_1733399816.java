
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.jsoup.parser.Parser.NamespaceHtml;

public class Element_valTest {

    @Test
    public void testVal_textarea() {
        Element element = new Element("textarea", NamespaceHtml);
        element.text("sample text");
        assertEquals("sample text", element.val());
    }

    @Test
    public void testVal_nonTextarea() {
        Element element = new Element("input", NamespaceHtml);
        element.attr("value", "sample value");
        assertEquals("sample value", element.val());
    }
}
