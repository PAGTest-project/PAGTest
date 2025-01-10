
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
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
    public void testEachTextWithTextElements() {
        List<String> expected = Arrays.asList("Text1", "Text2");
        assertEquals(expected, elements.eachText());
    }

    @Test
    public void testEachTextWithEmptyElements() {
        Document doc = Jsoup.parse("<div></div><div></div>");
        elements = new Elements(doc.select("div"));
        List<String> expected = Arrays.asList();
        assertEquals(expected, elements.eachText());
    }

    @Test
    public void testEachTextWithMixedElements() {
        Document doc = Jsoup.parse("<div>Text1</div><div></div><div>Text2</div><div></div>");
        elements = new Elements(doc.select("div"));
        List<String> expected = Arrays.asList("Text1", "Text2");
        assertEquals(expected, elements.eachText());
    }
}
