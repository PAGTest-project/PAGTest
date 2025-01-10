
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_textTest {

    @Test
    public void testTextWithSingleElement() {
        Document doc = Jsoup.parse("<div>Hello World</div>");
        Elements elements = doc.select("div");
        assertEquals("Hello World", elements.text());
    }

    @Test
    public void testTextWithMultipleElements() {
        Document doc = Jsoup.parse("<div>Hello</div><div>World</div>");
        Elements elements = doc.select("div");
        assertEquals("Hello World", elements.text());
    }

    @Test
    public void testTextWithNestedElements() {
        Document doc = Jsoup.parse("<div><span>Hello</span> <span>World</span></div>");
        Elements elements = doc.select("div");
        assertEquals("Hello World", elements.text());
    }

    @Test
    public void testTextWithNoElements() {
        Document doc = Jsoup.parse("<div></div>");
        Elements elements = doc.select("div");
        assertEquals("", elements.text());
    }

    @Test
    public void testTextWithMixedContent() {
        Document doc = Jsoup.parse("<div>Hello <span>World</span> <a href='#'>Link</a></div>");
        Elements elements = doc.select("div");
        assertEquals("Hello World Link", elements.text());
    }
}
