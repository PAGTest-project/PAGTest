
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_appendTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>First</p><p>Second</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testAppend() {
        elements.append("<span>Appended</span>");
        assertEquals("<p>First<span>Appended</span></p>", elements.get(0).outerHtml());
        assertEquals("<p>Second<span>Appended</span></p>", elements.get(1).outerHtml());
    }

    @Test
    public void testAppendEmptyString() {
        elements.append("");
        assertEquals("<p>First</p>", elements.get(0).outerHtml());
        assertEquals("<p>Second</p>", elements.get(1).outerHtml());
    }

    @Test
    public void testAppendToEmptyElements() {
        Elements emptyElements = new Elements();
        emptyElements.append("<span>Appended</span>");
        assertEquals(0, emptyElements.size());
    }

    @Test
    public void testAppendToSingleElement() {
        Elements singleElement = new Elements(elements.get(0));
        singleElement.append("<span>Appended</span>");
        assertEquals("<p>First<span>Appended</span></p>", singleElement.get(0).outerHtml());
    }

    @Test
    public void testAppendMultipleTimes() {
        elements.append("<span>Appended1</span>");
        elements.append("<span>Appended2</span>");
        assertEquals("<p>First<span>Appended1</span><span>Appended2</span></p>", elements.get(0).outerHtml());
        assertEquals("<p>Second<span>Appended1</span><span>Appended2</span></p>", elements.get(1).outerHtml());
    }
}
