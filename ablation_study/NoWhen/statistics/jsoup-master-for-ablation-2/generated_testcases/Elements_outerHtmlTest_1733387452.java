
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_outerHtmlTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testOuterHtmlWithSingleElement() {
        Element element = new Element("div");
        element.html("Hello, World!");
        elements.add(element);

        assertEquals("<div>Hello, World!</div>", elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithMultipleElements() {
        Element element1 = new Element("div");
        element1.html("First Element");
        Element element2 = new Element("span");
        element2.html("Second Element");
        elements.add(element1);
        elements.add(element2);

        assertEquals("<div>First Element</div>\n<span>Second Element</span>", elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithNoElements() {
        assertEquals("", elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithDocument() {
        String html = "<html><body><div>Hello</div><span>World</span></body></html>";
        Document doc = Jsoup.parse(html);
        elements = doc.body().children();

        assertEquals("<div>Hello</div>\n<span>World</span>", elements.outerHtml());
    }
}
