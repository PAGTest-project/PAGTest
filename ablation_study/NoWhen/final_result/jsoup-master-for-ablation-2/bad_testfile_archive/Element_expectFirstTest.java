
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Element_expectFirstTest {

    private Document doc;

    @BeforeEach
    public void setUp() {
        String html = "<body><div><p>One</p></div><div><p>Two</p></div><div>Three</div>";
        doc = Jsoup.parse(html);
    }

    @Test
    public void testExpectFirstWithMatchingElement() {
        Element div = doc.selectFirst("div");
        assertNotNull(div);

        Element result = div.expectFirst("p");
        assertNotNull(result);
        assertEquals("One", result.text());
        assertEquals("p", result.tagName());
    }

    @Test
    public void testExpectFirstWithNoMatchingElement() {
        Element div = doc.selectFirst("div");
        assertNotNull(div);

        assertThrows(IllegalArgumentException.class, () -> div.expectFirst("span"));
    }

    @Test
    public void testExpectFirstWithEmptyQuery() {
        Element div = doc.selectFirst("div");
        assertNotNull(div);

        assertThrows(IllegalArgumentException.class, () -> div.expectFirst(""));
    }

    @Test
    public void testExpectFirstWithNullQuery() {
        Element div = doc.selectFirst("div");
        assertNotNull(div);

        assertThrows(IllegalArgumentException.class, () -> div.expectFirst(null));
    }
}
