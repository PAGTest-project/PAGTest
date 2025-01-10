
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_eachAttrTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        String html = "<div class='test' data-attr='value1'></div>" +
                      "<div class='test' data-attr='value2'></div>" +
                      "<div class='test'></div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("div.test"));
    }

    @Test
    public void testEachAttrWithExistingAttribute() {
        List<String> attrs = elements.eachAttr("data-attr");
        assertEquals(2, attrs.size());
        assertEquals("value1", attrs.get(0));
        assertEquals("value2", attrs.get(1));
    }

    @Test
    public void testEachAttrWithNonExistingAttribute() {
        List<String> attrs = elements.eachAttr("non-existing-attr");
        assertEquals(0, attrs.size());
    }

    @Test
    public void testEachAttrWithMixedAttributes() {
        List<String> attrs = elements.eachAttr("class");
        assertEquals(3, attrs.size());
        assertEquals("test", attrs.get(0));
        assertEquals("test", attrs.get(1));
        assertEquals("test", attrs.get(2));
    }
}
