
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_htmlTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testHtmlWithSingleElement() {
        Element element = new Element("div");
        element.html("Hello, World!");
        elements.add(element);

        assertEquals("Hello, World!", elements.html());
    }

    @Test
    public void testHtmlWithMultipleElements() {
        Element element1 = new Element("div");
        element1.html("Hello");
        Element element2 = new Element("div");
        element2.html("World");
        elements.addAll(Arrays.asList(element1, element2));

        assertEquals("Hello\nWorld", elements.html());
    }

    @Test
    public void testHtmlWithNoElements() {
        assertEquals("", elements.html());
    }

    @Test
    public void testHtmlWithNestedElements() {
        Element parent = new Element("div");
        Element child = new Element("p");
        child.html("Nested");
        parent.appendChild(child);
        elements.add(parent);

        assertEquals("<div><p>Nested</p></div>", elements.html());
    }

    @Test
    public void testHtmlWithMixedContent() {
        Element div = new Element("div");
        div.html("Text");
        Element span = new Element("span");
        span.html("Span");
        elements.addAll(Arrays.asList(div, span));

        assertEquals("Text\n<span>Span</span>", elements.html());
    }
}
