
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_emptyTest {

    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>Text1</p><p>Text2</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testEmpty() {
        Elements result = elements.empty();
        assertEquals(2, result.size());
        for (Element element : result) {
            assertEquals("", element.text());
        }
    }
}
