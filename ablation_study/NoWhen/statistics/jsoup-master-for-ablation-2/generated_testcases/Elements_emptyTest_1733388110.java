
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_emptyTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p id='p1'>Text1</p><p id='p2'>Text2</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testEmpty() {
        Elements result = elements.empty();
        for (Element element : result) {
            assertEquals("", element.text());
        }
        assertEquals(elements, result);
    }

    @Test
    public void testEmptyWithNoChildren() {
        Document doc = Jsoup.parse("<div></div>");
        Elements emptyElements = new Elements(doc.select("div"));
        Elements result = emptyElements.empty();
        assertEquals(emptyElements, result);
    }
}
