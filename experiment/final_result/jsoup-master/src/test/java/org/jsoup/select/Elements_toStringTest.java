
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Elements_toStringTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>World</p></div>");
        elements = new Elements(doc.select("p"));
    }

    @Test
    public void testToString() {
        String expected = "<p>Hello</p>\n<p>World</p>";
        assertEquals(expected, elements.toString());
    }
}
