
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
        String html = "<body><div><p>One</p></div><div><p>Two</p></div><div>Three</div></body>";
        doc = Jsoup.parse(html);
    }

    @Test
    public void testExpectFirstWithMatchingElement() {
        Element body = doc.body();
        Element result = body.expectFirst("div p");
        assertNotNull(result);
        assertEquals("One", result.text());
    }

    @Test
    public void testExpectFirstWithNoMatchingElement() {
        Element body = doc.body();
        assertThrows(org.jsoup.helper.ValidationException.class, () -> {
            body.expectFirst("div span");
        });
    }
}
