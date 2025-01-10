
package org.jsoup;

import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Jsoup_cleanTest {

    private Safelist safelist;

    @BeforeEach
    public void setUp() {
        safelist = Safelist.basic();
    }

    @Test
    public void testCleanWithValidHtml() {
        String bodyHtml = "<p>Hello, world!</p>";
        String baseUri = "http://example.com";
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        assertEquals("<p>Hello, world!</p>", cleanedHtml);
    }

    @Test
    public void testCleanWithInvalidHtml() {
        String bodyHtml = "<script>alert('XSS')</script>";
        String baseUri = "http://example.com";
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        assertEquals("", cleanedHtml);
    }

    @Test
    public void testCleanWithEmptyHtml() {
        String bodyHtml = "";
        String baseUri = "http://example.com";
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        assertEquals("", cleanedHtml);
    }

    @Test
    public void testCleanWithNullHtml() {
        String bodyHtml = null;
        String baseUri = "http://example.com";
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        assertEquals("", cleanedHtml);
    }

    @Test
    public void testCleanWithRelativeLinks() {
        String bodyHtml = "<a href=\"/relative\">Link</a>";
        String baseUri = "http://example.com";
        String cleanedHtml = Jsoup.clean(bodyHtml, baseUri, safelist);
        assertEquals("<a href=\"http://example.com/relative\">Link</a>", cleanedHtml);
    }
}
