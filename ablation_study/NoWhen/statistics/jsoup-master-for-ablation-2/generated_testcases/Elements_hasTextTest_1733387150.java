
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_hasTextTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testHasTextWithTextNodes() {
        Element element1 = new Element("div");
        element1.appendChild(new TextNode("Hello"));
        elements.add(element1);

        assertTrue(elements.hasText());
    }

    @Test
    public void testHasTextWithoutTextNodes() {
        Element element1 = new Element("div");
        elements.add(element1);

        assertFalse(elements.hasText());
    }

    @Test
    public void testHasTextWithMixedNodes() {
        Element element1 = new Element("div");
        element1.appendChild(new TextNode("Hello"));
        elements.add(element1);

        Element element2 = new Element("div");
        elements.add(element2);

        assertTrue(elements.hasText());
    }

    @Test
    public void testHasTextWithEmptyElements() {
        assertFalse(elements.hasText());
    }

    @Test
    public void testHasTextWithMultipleTextNodes() {
        Element element1 = new Element("div");
        element1.appendChild(new TextNode("Hello"));
        elements.add(element1);

        Element element2 = new Element("div");
        element2.appendChild(new TextNode("World"));
        elements.add(element2);

        assertTrue(elements.hasText());
    }
}
