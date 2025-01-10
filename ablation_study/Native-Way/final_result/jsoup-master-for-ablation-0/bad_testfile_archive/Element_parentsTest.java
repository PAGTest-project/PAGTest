
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Element_parentsTest {

    private Element element;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        element = doc.select("div").first();
    }

    @Test
    public void testParents() {
        Elements parents = element.parents();
        assertEquals(1, parents.size());
        assertEquals("html", parents.first().tagName());
    }

    @Test
    public void testParentsWithNoParent() {
        Document doc = Jsoup.parse("<div></div>");
        Element rootElement = doc.select("div").first();
        Elements parents = rootElement.parents();
        assertEquals(0, parents.size());
    }

    @Test
    public void testParentsWithMultipleLevels() {
        Document doc = Jsoup.parse("<html><body><div><p>One</p></div></body></html>");
        Element pElement = doc.select("p").first();
        Elements parents = pElement.parents();
        assertEquals(3, parents.size());
        assertEquals("div", parents.get(0).tagName());
        assertEquals("body", parents.get(1).tagName());
        assertEquals("html", parents.get(2).tagName());
    }
}
