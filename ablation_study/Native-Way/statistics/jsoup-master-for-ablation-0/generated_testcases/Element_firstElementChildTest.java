
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_firstElementChildTest {

    private Document doc;

    @BeforeEach
    public void setUp() {
        String html = "<body><div><p>One</p><span>Two</span></div><div>Three</div></body>";
        doc = Jsoup.parse(html);
    }

    @Test
    public void testFirstElementChildWithElementChild() {
        Element div = doc.selectFirst("div");
        assertNotNull(div);
        Element firstElementChild = div.firstElementChild();
        assertNotNull(firstElementChild);
        assertEquals("p", firstElementChild.tagName());
        assertEquals("One", firstElementChild.text());
    }

    @Test
    public void testFirstElementChildWithoutElementChild() {
        Element div = doc.select("div").last();
        assertNotNull(div);
        Element firstElementChild = div.firstElementChild();
        assertNull(firstElementChild);
    }

    @Test
    public void testFirstElementChildWithNoChildren() {
        Element emptyDiv = new Element("div");
        Element firstElementChild = emptyDiv.firstElementChild();
        assertNull(firstElementChild);
    }

    @Test
    public void testFirstElementChildWithMixedChildren() {
        String html = "<div><p>One</p><span>Two</span><text>Three</text></div>";
        Document mixedDoc = Jsoup.parse(html);
        Element div = mixedDoc.selectFirst("div");
        assertNotNull(div);
        Element firstElementChild = div.firstElementChild();
        assertNotNull(firstElementChild);
        assertEquals("p", firstElementChild.tagName());
        assertEquals("One", firstElementChild.text());
    }
}
