
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_notTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div class='test'><p class='exclude'>Exclude</p><p>Include</p></div>");
        elements = new Elements(doc.select("div.test p"));
    }

    @Test
    public void testNot() {
        Elements result = elements.not(".exclude");
        assertEquals(1, result.size());
        assertEquals("Include", result.get(0).text());
    }
}
