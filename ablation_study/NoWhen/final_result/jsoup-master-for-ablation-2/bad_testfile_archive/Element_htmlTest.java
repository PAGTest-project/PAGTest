
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_htmlTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        String html = "<div><p>Test</p></div>";
        Document doc = Jsoup.parse(html);
        element = doc.selectFirst("div");
    }

    @Test
    public void testHtmlWithPrettyPrint() {
        element.outputSettings().prettyPrint(true);
        String expectedHtml = "<div>\n <p>Test</p>\n</div>";
        assertEquals(expectedHtml, element.html().trim());
    }

    @Test
    public void testHtmlWithoutPrettyPrint() {
        element.outputSettings().prettyPrint(false);
        String expectedHtml = "<div><p>Test</p></div>";
        assertEquals(expectedHtml, element.html());
    }
}
