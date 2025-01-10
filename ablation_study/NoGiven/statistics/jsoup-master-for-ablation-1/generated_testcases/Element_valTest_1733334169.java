
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class Element_valTest {

    @Test
    void testVal_textarea() {
        Element element = mock(Element.class);
        when(element.elementIs("textarea", Parser.NamespaceHtml)).thenReturn(true);
        when(element.text()).thenReturn("textarea text");

        String result = element.val();

        assertEquals("textarea text", result);
    }

    @Test
    void testVal_notTextarea() {
        Element element = mock(Element.class);
        when(element.elementIs("textarea", Parser.NamespaceHtml)).thenReturn(false);
        when(element.attr("value")).thenReturn("attribute value");

        String result = element.val();

        assertEquals("attribute value", result);
    }
}
