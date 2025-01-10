
package org.jsoup.nodes;

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
    public void testHtmlWithPrettyPrint() {
        element.text("Test Text");
        element.outputSettings().prettyPrint(true);
        String html = element.html();
        assertEquals("<div>Test Text</div>", html);
    }

    @Test
    public void testHtmlWithoutPrettyPrint() {
        element.text("Test Text");
        element.outputSettings().prettyPrint(false);
        String html = element.html();
        assertEquals("<div>Test Text</div>", html);
    }

    @Test
    public void testHtmlWithChildNodes() {
        Element child = new Element("p");
        child.text("Child Text");
        element.appendChild(child);
        element.outputSettings().prettyPrint(true);
        String html = element.html();
        assertEquals("<div>\n <p>Child Text</p>\n</div>", html);
    }

    @Test
    public void testHtmlWithAttributes() {
        element.attr("class", "test");
        element.text("Test Text");
        element.outputSettings().prettyPrint(true);
        String html = element.html();
        assertEquals("<div class=\"test\">Test Text</div>", html);
    }

    @Test
    public void testHtmlWithEmptyElement() {
        element.outputSettings().prettyPrint(true);
        String html = element.html();
        assertEquals("<div></div>", html);
    }
}
