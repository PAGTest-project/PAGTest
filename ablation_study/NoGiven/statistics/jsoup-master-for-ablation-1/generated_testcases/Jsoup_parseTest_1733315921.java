
package org.jsoup;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Jsoup_parseTest {
    private URL testUrl;

    @BeforeEach
    public void setUp() throws Exception {
        testUrl = new URL("https://example.com");
    }

    @Test
    public void testParseWithValidUrlAndTimeout() throws IOException {
        Document doc = Jsoup.parse(testUrl, 5000);
        assertNotNull(doc);
        assertEquals("Example Domain", doc.title());
    }

    @Test
    public void testParseWithInvalidUrl() {
        URL invalidUrl = null;
        try {
            Jsoup.parse(invalidUrl, 5000);
        } catch (IOException e) {
            assertEquals("java.net.MalformedURLException: no protocol: null", e.getMessage());
        }
    }

    @Test
    public void testParseWithZeroTimeout() throws IOException {
        Document doc = Jsoup.parse(testUrl, 0);
        assertNotNull(doc);
        assertEquals("Example Domain", doc.title());
    }
}
