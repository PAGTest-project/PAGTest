
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_removeAttrTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div id='test' class='test-class' data-attr='test-data'></div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testRemoveAttr() {
        elements.removeAttr("class");
        for (Element element : elements) {
            assertFalse(element.hasAttr("class"));
        }
    }

    @Test
    public void testRemoveAttrMultipleElements() {
        Document doc = Jsoup.parse("<div id='test1' class='test-class1'></div><div id='test2' class='test-class2'></div>");
        elements = new Elements(doc.select("div"));
        elements.removeAttr("class");
        for (Element element : elements) {
            assertFalse(element.hasAttr("class"));
        }
    }

    @Test
    public void testRemoveAttrNonExistent() {
        elements.removeAttr("non-existent-attr");
        for (Element element : elements) {
            assertTrue(element.hasAttr("data-attr"));
        }
    }

    @Test
    public void testRemoveAttrEmptyElements() {
        elements = new Elements();
        elements.removeAttr("class");
        assertFalse(elements.hasAttr("class"));
    }

    @Test
    public void testRemoveAttrWithNullKey() {
        assertThrows(IllegalArgumentException.class, () -> elements.removeAttr(null));
    }

    @Test
    public void testRemoveAttrWithEmptyKey() {
        elements.removeAttr("");
        for (Element element : elements) {
            assertTrue(element.hasAttr("data-attr"));
        }
    }
}
