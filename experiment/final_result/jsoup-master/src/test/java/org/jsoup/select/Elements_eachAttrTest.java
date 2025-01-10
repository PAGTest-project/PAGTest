
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
        String html = "<div class='test' data-attr='value1'></div><div data-attr='value2'></div>";
        Document doc = Jsoup.parse(html);
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testEachAttr() {
        List<String> attrs = elements.eachAttr("data-attr");
        assertEquals(2, attrs.size());
        assertEquals("value1", attrs.get(0));
        assertEquals("value2", attrs.get(1));
    }
}
