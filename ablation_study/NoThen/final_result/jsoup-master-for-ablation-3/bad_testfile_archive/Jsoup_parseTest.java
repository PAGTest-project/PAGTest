
package org.jsoup;

import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

public class Jsoup_parseTest {
    private URL validUrl;
    private URL invalidUrl;

    @BeforeEach
    public void setUp() throws Exception {
        validUrl = new URL("https://example.com");
        invalidUrl = new URL("https://invalid-url-example.com");
    }

    @Test
    public void testParseValidUrl() throws IOException {
        Document doc = Jsoup.parse(validUrl, 5000);
        assertNotNull(doc);
        assertTrue(doc.title().length() > 0);
    }

    @Test
    public void testParseInvalidUrl() {
        assertThrows(IOException.class, () -> {
            Jsoup.parse(invalidUrl, 5000);
        });
    }

    @Test
    public void testParseTimeout() {
        assertThrows(IOException.class, () -> {
            Jsoup.parse(validUrl, 1);
        });
    }
}
