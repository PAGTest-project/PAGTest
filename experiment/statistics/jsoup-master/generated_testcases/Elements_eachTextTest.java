
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
        Document doc = Jsoup.parse("<div><p>Text1</p><p></p><p>Text2</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testEachText() {
        List<String> texts = elements.eachText();
        assertEquals(2, texts.size());
        assertEquals("Text1", texts.get(0));
        assertEquals("Text2", texts.get(1));
    }
}
