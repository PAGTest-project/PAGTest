
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_htmlTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        element = new Element("div");
    }

    @Test
    public void testHtmlNoPretty() {
        element.text("Hello, World!");
        element.outputSettings().prettyPrint(false);
        assertEquals("<div>Hello, World!</div>", element.html());
    }

    @Test
    public void testHtmlPretty() {
        element.text("Hello, World!");
        element.outputSettings().prettyPrint(true);
        assertEquals("<div>\n Hello, World!\n</div>", element.html());
    }

    @Test
    public void testHtmlWithChildElements() {
        Element child = new Element("span");
        child.text("Child");
        element.appendChild(child);
        element.outputSettings().prettyPrint(false);
        assertEquals("<div><span>Child</span></div>", element.html());
    }

    @Test
    public void testHtmlWithAttributes() {
        element.attr("class", "test");
        element.text("Hello, World!");
        element.outputSettings().prettyPrint(false);
        assertEquals("<div class=\"test\">Hello, World!</div>", element.html());
    }
}
