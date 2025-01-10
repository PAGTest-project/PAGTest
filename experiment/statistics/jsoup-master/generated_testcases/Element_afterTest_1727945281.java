
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Element_afterTest {

    @Test
    public void testAfterWithString() {
        Element element = new Element("div");
        Element result = element.after("<p>Test</p>");
        assertEquals("<p>Test</p>", result.outerHtml());
    }
}
