
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_attrTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div id='test' data-attr='value'></div><div></div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testAttrWithExistingAttribute() {
        String attributeKey = "data-attr";
        String result = elements.attr(attributeKey);
        assertEquals("value", result);
    }

    @Test
    public void testAttrWithNonExistingAttribute() {
        String attributeKey = "non-existing-attr";
        String result = elements.attr(attributeKey);
        assertEquals("", result);
    }
}
