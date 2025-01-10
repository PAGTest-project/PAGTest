
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_cloneTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>One</p><p>Two</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testCloneWithEmptyElements() {
        Elements emptyElements = new Elements();
        Elements clonedEmptyElements = emptyElements.clone();
        assertEquals(0, clonedEmptyElements.size());
    }

    @Test
    public void testCloneWithNonEmptyElements() {
        Elements clonedElements = elements.clone();
        assertEquals(elements.size(), clonedElements.size());
        for (int i = 0; i < elements.size(); i++) {
            assertEquals(elements.get(i).outerHtml(), clonedElements.get(i).outerHtml());
        }
    }

    @Test
    public void testCloneWithModifiedElements() {
        elements.get(0).addClass("testClass");
        Elements clonedElements = elements.clone();
        assertEquals(elements.size(), clonedElements.size());
        for (int i = 0; i < elements.size(); i++) {
            assertEquals(elements.get(i).outerHtml(), clonedElements.get(i).outerHtml());
        }
    }

    @Test
    public void testCloneWithNestedElements() {
        Document doc = Jsoup.parse("<div><p><span>One</span></p><p>Two</p></div>");
        elements = doc.select("p");
        Elements clonedElements = elements.clone();
        assertEquals(elements.size(), clonedElements.size());
        for (int i = 0; i < elements.size(); i++) {
            assertEquals(elements.get(i).outerHtml(), clonedElements.get(i).outerHtml());
        }
    }
}
