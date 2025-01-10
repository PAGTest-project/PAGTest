
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Document_outerHtmlTest {
    private Document document;

    @BeforeEach
    public void setUp() {
        document = Jsoup.parse("<html><head></head><body><div>Test</div></body></html>");
        document.outputSettings().prettyPrint(false); // Disable pretty printing
    }

    @Test
    public void testOuterHtml() {
        String expectedHtml = "<html><head></head><body><div>Test</div></body></html>";
        assertEquals(expectedHtml, document.outerHtml());
    }
}
