
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_lastElementChildTest {
    private Document doc;

    @BeforeEach
    public void setUp() {
        String html = "<body><div><p>One</p><p>Two</p></div><div>Three</div></body>";
        doc = Jsoup.parse(html);
    }

    @Test
    public void testLastElementChildWithElement() {
        Element div = doc.selectFirst("div");
        assertNotNull(div);
        Element lastChild = div.lastElementChild();
        assertNotNull(lastChild);
        assertEquals("p", lastChild.tagName());
        assertEquals("Two", lastChild.text());
    }

    @Test
    public void testLastElementChildWithNoElement() {
        Element div = doc.select("div").get(1);
        assertNotNull(div);
        Element lastChild = div.lastElementChild();
        assertNull(lastChild);
    }

    @Test
    public void testLastElementChildWithTextNode() {
        String html = "<body><div>Text<p>One</p></div></body>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        assertNotNull(div);
        Element lastChild = div.lastElementChild();
        assertNotNull(lastChild);
        assertEquals("p", lastChild.tagName());
        assertEquals("One", lastChild.text());
    }

    @Test
    public void testLastElementChildWithMixedNodes() {
        String html = "<body><div>Text<p>One</p><span>Two</span></div></body>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        assertNotNull(div);
        Element lastChild = div.lastElementChild();
        assertNotNull(lastChild);
        assertEquals("span", lastChild.tagName());
        assertEquals("Two", lastChild.text());
    }

    @Test
    public void testLastElementChildWithNoChildren() {
        String html = "<body><div></div></body>";
        Document doc = Jsoup.parse(html);
        Element div = doc.selectFirst("div");
        assertNotNull(div);
        Element lastChild = div.lastElementChild();
        assertNull(lastChild);
    }
}
