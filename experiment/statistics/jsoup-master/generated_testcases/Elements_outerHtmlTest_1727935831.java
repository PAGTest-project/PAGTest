
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_outerHtmlTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        elements = new Elements();
    }

    @Test
    public void testOuterHtml() {
        // Given
        Element element1 = new Element("div");
        element1.append("<p>Hello</p>");
        Element element2 = new Element("span");
        element2.append("<a href='#'>Link</a>");
        elements.addAll(Arrays.asList(element1, element2));

        // When
        String result = elements.outerHtml();

        // Then
        String expected = "<div><p>Hello</p></div>\n<span><a href='#'>Link</a></span>";
        assertEquals(expected, result);
    }
}
