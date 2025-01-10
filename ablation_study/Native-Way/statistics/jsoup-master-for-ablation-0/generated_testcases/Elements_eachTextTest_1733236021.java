
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_eachTextTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div>Text1</div><div></div><div>Text2</div>");
        elements = new Elements(doc.select("div"));
    }

    @Test
    public void testEachTextWithTextNodes() {
        List<String> texts = elements.eachText();
        assertEquals(2, texts.size());
        assertEquals("Text1", texts.get(0));
        assertEquals("Text2", texts.get(1));
    }

    @Test
    public void testEachTextWithNoTextNodes() {
        Document doc = Jsoup.parse("<div></div><div></div>");
        Elements emptyElements = new Elements(doc.select("div"));
        List<String> texts = emptyElements.eachText();
        assertEquals(0, texts.size());
    }

    @Test
    public void testEachTextWithMixedNodes() {
        Document doc = Jsoup.parse("<div>Text1</div><div></div><div>Text2</div><div>Text3</div>");
        Elements mixedElements = new Elements(doc.select("div"));
        List<String> texts = mixedElements.eachText();
        assertEquals(3, texts.size());
        assertEquals("Text1", texts.get(0));
        assertEquals("Text2", texts.get(1));
        assertEquals("Text3", texts.get(2));
    }
}
