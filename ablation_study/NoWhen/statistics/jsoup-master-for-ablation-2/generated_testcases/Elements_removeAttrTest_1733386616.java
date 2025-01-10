
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
        Document doc = Jsoup.parse("<div id='test' class='test-class' data-attr='test-data'><p class='inner-class' data-inner='inner-data'></p></div>");
        elements = new Elements(doc.select("div, p"));
    }

    @Test
    public void testRemoveAttr() {
        elements.attr("data-attr", "test-data");
        elements.attr("data-inner", "inner-data");

        Elements result = elements.removeAttr("data-attr");

        assertFalse(result.hasAttr("data-attr"));
        assertTrue(result.hasAttr("data-inner"));
    }

    @Test
    public void testRemoveAttrAllElements() {
        elements.attr("data-attr", "test-data");
        elements.attr("data-inner", "inner-data");

        Elements result = elements.removeAttr("data-attr");

        for (Element element : result) {
            assertFalse(element.hasAttr("data-attr"));
        }
    }

    @Test
    public void testRemoveAttrNoExistingAttribute() {
        Elements result = elements.removeAttr("nonexistent-attr");

        for (Element element : result) {
            assertFalse(element.hasAttr("nonexistent-attr"));
        }
    }
}
