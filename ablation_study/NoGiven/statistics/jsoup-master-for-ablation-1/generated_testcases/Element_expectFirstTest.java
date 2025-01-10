
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
    public void testExpectFirstWithExistingElement() {
        Element body = doc.body();
        Element firstDiv = body.expectFirst("div");
        assertNotNull(firstDiv);
        assertEquals("div", firstDiv.tagName());
    }

    @Test
    public void testExpectFirstWithNonExistingElement() {
        Element body = doc.body();
        try {
            Element nonExistingElement = body.expectFirst("nonexistent");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }

    @Test
    public void testExpectFirstWithNestedElement() {
        Element body = doc.body();
        Element firstP = body.expectFirst("div > p");
        assertNotNull(firstP);
        assertEquals("p", firstP.tagName());
        assertEquals("One", firstP.text());
    }

    @Test
    public void testExpectFirstWithEmptyDocument() {
        Document emptyDoc = Jsoup.parse("");
        try {
            Element emptyElement = emptyDoc.expectFirst("div");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Expected exception
        }
    }
}
