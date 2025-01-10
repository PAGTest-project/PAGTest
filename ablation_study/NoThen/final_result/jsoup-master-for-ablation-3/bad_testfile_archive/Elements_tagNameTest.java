
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Elements_tagNameTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p id='p1'>Paragraph 1</p><p id='p2'>Paragraph 2</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testTagName() {
        elements.tagName("span");
        for (Element element : elements) {
            assertEquals("span", element.tagName());
        }
    }

    @Test
    public void testTagNameWithEmptyElements() {
        Elements emptyElements = new Elements();
        emptyElements.tagName("span");
        assertEquals(0, emptyElements.size());
    }

    @Test
    public void testTagNameWithSingleElement() {
        Element singleElement = Jsoup.parse("<p>Single Paragraph</p>").select("p").first();
        Elements singleElementList = new Elements(singleElement);
        singleElementList.tagName("span");
        assertEquals("span", singleElement.tagName());
    }

    @Test
    public void testTagNameWithDifferentTagNames() {
        Document doc = Jsoup.parse("<div><p id='p1'>Paragraph 1</p><span id='s1'>Span 1</span></div>");
        Elements mixedElements = doc.select("p, span");
        mixedElements.tagName("div");
        for (Element element : mixedElements) {
            assertEquals("div", element.tagName());
        }
    }
}
