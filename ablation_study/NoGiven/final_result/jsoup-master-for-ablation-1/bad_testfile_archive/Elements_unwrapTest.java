
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_unwrapTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>World</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testUnwrap() {
        elements.unwrap();
        assertEquals("Hello World", elements.text());
    }

    @Test
    public void testUnwrapWithWrappedElements() {
        elements.wrap("<div class='wrapper'></div>");
        elements.unwrap();
        assertEquals("Hello World", elements.text());
    }

    @Test
    public void testUnwrapWithEmptyElements() {
        elements.empty();
        elements.unwrap();
        assertEquals("", elements.text());
    }
}
