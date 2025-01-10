
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
    public void testExpectFirstWithValidQuery() {
        Element body = doc.body();
        Element firstDiv = body.expectFirst("div");
        assertNotNull(firstDiv);
        assertEquals("div", firstDiv.tagName());
    }

    @Test
    public void testExpectFirstWithInvalidQuery() {
        Element body = doc.body();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            body.expectFirst("nonexistent");
        });
        String expectedMessage = "No elements matched the query 'nonexistent' on element 'body'.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testExpectFirstWithEmptyDocument() {
        Document emptyDoc = Jsoup.parse("");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            emptyDoc.expectFirst("div");
        });
        String expectedMessage = "No elements matched the query 'div' in the document.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
