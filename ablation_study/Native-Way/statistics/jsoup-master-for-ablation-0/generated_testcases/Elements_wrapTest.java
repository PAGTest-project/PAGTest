
package org.jsoup.select;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Elements_wrapTest {
    private Elements elements;

    @BeforeEach
    public void setUp() {
        Document doc = Jsoup.parse("<div><p>Hello</p><p>World</p></div>");
        elements = doc.select("p");
    }

    @Test
    public void testWrapWithValidHtml() {
        Elements result = elements.wrap("<div class='wrapper'></div>");
        assertEquals("<div class=\"wrapper\"><p>Hello</p></div><div class=\"wrapper\"><p>World</p></div>", result.outerHtml().replaceAll("\\s+", ""));
    }

    @Test
    public void testWrapWithEmptyHtml() {
        assertThrows(IllegalArgumentException.class, () -> elements.wrap(""));
    }

    @Test
    public void testWrapWithNullHtml() {
        assertThrows(IllegalArgumentException.class, () -> elements.wrap(null));
    }

    @Test
    public void testWrapWithInvalidHtml() {
        assertThrows(IllegalArgumentException.class, () -> elements.wrap("invalid html"));
    }
}
