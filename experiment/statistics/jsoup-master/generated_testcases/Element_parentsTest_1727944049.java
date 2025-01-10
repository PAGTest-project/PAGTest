
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.parser.ParseSettings;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_parentsTest {
    private Element element;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><span></span></div>");
        element = doc.body().child(0);
    }

    @Test
    public void testParents() {
        Elements parents = element.parents();
        assertEquals(2, parents.size());
        assertEquals("body", parents.get(0).tagName());
        assertEquals("html", parents.get(1).tagName());
    }

    @Test
    public void testParentsWithRoot() {
        Element rootElement = Jsoup.parse("<html><body><div></div></body></html>").body().child(0);
        Elements parents = rootElement.parents();
        assertEquals(2, parents.size());
        assertEquals("body", parents.get(0).tagName());
        assertEquals("html", parents.get(1).tagName());
    }
}
