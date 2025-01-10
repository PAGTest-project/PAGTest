
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
        elements = new Elements();
    }

    @Test
    public void testEachTextWithTextNodes() {
        Element element1 = new Element("div");
        element1.appendText("Hello");
        Element element2 = new Element("div");
        element2.appendText("World");

        elements.add(element1);
        elements.add(element2);

        List<String> expected = Arrays.asList("Hello", "World");
        assertEquals(expected, elements.eachText());
    }

    @Test
    public void testEachTextWithNoTextNodes() {
        Element element1 = new Element("div");
        Element element2 = new Element("div");

        elements.add(element1);
        elements.add(element2);

        List<String> expected = Arrays.asList();
        assertEquals(expected, elements.eachText());
    }

    @Test
    public void testEachTextWithMixedNodes() {
        Element element1 = new Element("div");
        element1.appendText("Hello");
        Element element2 = new Element("div");
        element2.appendText("World");
        Element element3 = new Element("div");

        elements.add(element1);
        elements.add(element2);
        elements.add(element3);

        List<String> expected = Arrays.asList("Hello", "World");
        assertEquals(expected, elements.eachText());
    }

    @Test
    public void testEachTextWithDocument() {
        String html = "<div>Hello</div><div>World</div><div></div>";
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("div");

        List<String> expected = Arrays.asList("Hello", "World");
        assertEquals(expected, elements.eachText());
    }
}
