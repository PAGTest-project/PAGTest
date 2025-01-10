
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_parentsTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testParentsWithSingleElement() {
        Element element = new Element("div");
        elements.add(element);
        Elements parents = elements.parents();
        assertEquals(0, parents.size());
    }

    @Test
    public void testParentsWithNestedElements() {
        Document doc = Jsoup.parse("<div><p><a></a></p></div>");
        elements.add(doc.select("a").first());
        Elements parents = elements.parents();
        assertEquals(2, parents.size());
        assertEquals("p", parents.get(0).tagName());
        assertEquals("div", parents.get(1).tagName());
    }

    @Test
    public void testParentsWithMultipleElements() {
        Document doc = Jsoup.parse("<div><p><a></a></p></div><div><p><a></a></p></div>");
        elements.addAll(doc.select("a"));
        Elements parents = elements.parents();
        assertEquals(4, parents.size());
        assertEquals("p", parents.get(0).tagName());
        assertEquals("div", parents.get(1).tagName());
        assertEquals("p", parents.get(2).tagName());
        assertEquals("div", parents.get(3).tagName());
    }

    @Test
    public void testParentsWithNoElements() {
        Elements parents = elements.parents();
        assertEquals(0, parents.size());
    }

    @Test
    public void testParentsWithFilteredElements() {
        Document doc = Jsoup.parse("<div class='test'><p><a></a></p></div><div><p><a></a></p></div>");
        elements.addAll(doc.select("a"));
        elements.filter(new NodeFilter() {
            @Override
            public FilterResult head(Node node, int depth) {
                if (node instanceof Element && ((Element) node).hasClass("test")) {
                    return FilterResult.REMOVE;
                }
                return FilterResult.CONTINUE;
            }

            @Override
            public FilterResult tail(Node node, int depth) {
                return FilterResult.CONTINUE;
            }
        });
        Elements parents = elements.parents();
        assertEquals(2, parents.size());
        assertEquals("p", parents.get(0).tagName());
        assertEquals("div", parents.get(1).tagName());
    }
}
