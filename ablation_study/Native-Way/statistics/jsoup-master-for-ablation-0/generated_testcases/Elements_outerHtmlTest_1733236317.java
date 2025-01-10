
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
        element.appendText("Hello, World!");
        elements.add(element);

        String expectedHtml = "<div>Hello, World!</div>";
        assertEquals(expectedHtml, elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithMultipleElements() {
        Element element1 = new Element("div");
        element1.appendText("First Element");
        Element element2 = new Element("span");
        element2.appendText("Second Element");
        elements.add(element1);
        elements.add(element2);

        String expectedHtml = "<div>First Element</div>\n<span>Second Element</span>";
        assertEquals(expectedHtml, elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithEmptyElements() {
        String expectedHtml = "";
        assertEquals(expectedHtml, elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithNestedElements() {
        Element parent = new Element("div");
        Element child = new Element("p");
        child.appendText("Nested Content");
        parent.appendChild(child);
        elements.add(parent);

        String expectedHtml = "<div><p>Nested Content</p></div>";
        assertEquals(expectedHtml, elements.outerHtml());
    }

    @Test
    public void testOuterHtmlWithDocument() {
        String html = "<html><body><div>Content</div></body></html>";
        Document doc = Jsoup.parse(html);
        elements = doc.select("div");

        String expectedHtml = "<div>Content</div>";
        assertEquals(expectedHtml, elements.outerHtml());
    }
}
