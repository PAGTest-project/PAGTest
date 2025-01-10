
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_appendTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element(Tag.valueOf("div"), "");
    }

    @Test
    public void testAppend() {
        String html = "<p>Hello, World!</p>";
        element.append(html);
        assertEquals("<div><p>Hello, World!</p></div>", element.outerHtml());
    }

    @Test
    public void testAppendNull() {
        assertThrows(IllegalArgumentException.class, () -> element.append(null));
    }
}
