
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_resolveTest {

    @Test
    void testResolveWithRelativeUrl() throws MalformedURLException {
        URL base = new URL("https://example.com/path/");
        String relUrl = "subpath/file.html";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("https://example.com/path/subpath/file.html", resolvedUrl.toString());
    }

    @Test
    void testResolveWithQueryParameter() throws MalformedURLException {
        URL base = new URL("https://example.com/path/file.html");
        String relUrl = "?query=value";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("https://example.com/path/file.html?query=value", resolvedUrl.toString());
    }

    @Test
    void testResolveWithFragment() throws MalformedURLException {
        URL base = new URL("https://example.com/path/file.html");
        String relUrl = "#fragment";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("https://example.com/path/file.html#fragment", resolvedUrl.toString());
    }

    @Test
    void testResolveWithControlChars() throws MalformedURLException {
        URL base = new URL("https://example.com/path/");
        String relUrl = "\u0000subpath/file.html";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("https://example.com/path/subpath/file.html", resolvedUrl.toString());
    }

    @Test
    void testResolveWithDotSegments() throws MalformedURLException {
        URL base = new URL("https://example.com/path/");
        String relUrl = "./subpath/file.html";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("https://example.com/path/subpath/file.html", resolvedUrl.toString());
    }

    @Test
    void testResolveWithEmptyRelativeUrl() throws MalformedURLException {
        URL base = new URL("https://example.com/path/");
        String relUrl = "";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("https://example.com/path/", resolvedUrl.toString());
    }

    @Test
    void testResolveWithAbsoluteUrl() throws MalformedURLException {
        URL base = new URL("https://example.com/path/");
        String relUrl = "https://another.com/file.html";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("https://another.com/file.html", resolvedUrl.toString());
    }

    @Test
    void testResolveWithInvalidBaseUrl() {
        URL base = null;
        String relUrl = "subpath/file.html";
        assertThrows(MalformedURLException.class, () -> StringUtil.resolve(base, relUrl));
    }

    @Test
    void testResolveWithInvalidRelativeUrl() {
        URL base = new URL("https://example.com/path/");
        String relUrl = null;
        assertThrows(MalformedURLException.class, () -> StringUtil.resolve(base, relUrl));
    }
}
