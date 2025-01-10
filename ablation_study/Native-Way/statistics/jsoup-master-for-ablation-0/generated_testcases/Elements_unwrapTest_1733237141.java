
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_unwrapTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testUnwrap() {
        Elements unwrappedElements = elements.unwrap();
        assertEquals(elements, unwrappedElements);
        assertEquals(0, elements.size());
    }

    @Test
    public void testUnwrapWithNoChildren() {
        Document doc = Jsoup.parse("<div></div>");
        Elements emptyElements = new Elements(doc.select("div"));
        Elements unwrappedElements = emptyElements.unwrap();
        assertEquals(emptyElements, unwrappedElements);
        assertEquals(0, emptyElements.size());
    }

    @Test
    public void testUnwrapWithSingleChild() {
        Document doc = Jsoup.parse("<div><p>One</p></div>");
        Elements singleElement = new Elements(doc.select("p"));
        Elements unwrappedElements = singleElement.unwrap();
        assertEquals(singleElement, unwrappedElements);
        assertEquals(0, singleElement.size());
    }
}
