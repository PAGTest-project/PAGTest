
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Elements_hasTextTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testHasTextWithNoText() {
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        elements.addAll(Arrays.asList(element1, element2));
        assertFalse(elements.hasText());
    }

    @Test
    public void testHasTextWithText() {
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        element2.appendChild(new TextNode("Sample Text"));
        elements.addAll(Arrays.asList(element1, element2));
        assertTrue(elements.hasText());
    }

    @Test
    public void testHasTextWithMixedContent() {
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        element2.appendChild(new TextNode(""));
        Element element3 = new Element("p");
        element3.appendChild(new TextNode("Another Text"));
        elements.addAll(Arrays.asList(element1, element2, element3));
        assertTrue(elements.hasText());
    }

    @Test
    public void testHasTextWithEmptyElements() {
        Element element1 = new Element("div");
        Element element2 = new Element("span");
        element2.appendChild(new TextNode(""));
        elements.addAll(Arrays.asList(element1, element2));
        assertFalse(elements.hasText());
    }
}
