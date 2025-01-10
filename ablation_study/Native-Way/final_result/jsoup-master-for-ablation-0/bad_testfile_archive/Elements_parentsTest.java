
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_parentsTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testParents() {
        Elements parents = elements.parents();
        assertEquals(1, parents.size());
        assertEquals("div", parents.first().tagName());
    }

    @Test
    public void testParentsWithNoParents() {
        Elements noParents = new Elements(new Element("p"));
        Elements parents = noParents.parents();
        assertTrue(parents.isEmpty());
    }

    @Test
    public void testParentsWithMultipleElements() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div><div><p>Three</p></div>");
        elements = new Elements(doc.select("p"));
        Elements parents = elements.parents();
        assertEquals(2, parents.size());
        assertEquals("div", parents.get(0).tagName());
        assertEquals("div", parents.get(1).tagName());
    }

    @Test
    public void testParentsWithNestedElements() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p><p>Two</p></div>");
        elements = new Elements(doc.select("span"));
        Elements parents = elements.parents();
        assertEquals(2, parents.size());
        assertEquals("p", parents.get(0).tagName());
        assertEquals("div", parents.get(1).tagName());
    }

    @Test
    public void testParentsWithDuplicateParents() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        elements = new Elements(doc.select("p"));
        Elements parents = elements.parents();
        assertEquals(1, parents.size());
        assertEquals("div", parents.first().tagName());
    }
}
