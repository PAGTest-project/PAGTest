
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_resolveTest {

    @Test
    void testResolveWithValidRelativeUrl() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "?foo";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file?foo", resolvedUrl.toString());
    }

    @Test
    void testResolveWithInvalidRelativeUrl() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "?foo\u0000";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file?foo", resolvedUrl.toString());
    }

    @Test
    void testResolveWithControlChars() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "?foo\u0001\u0002";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file?foo", resolvedUrl.toString());
    }

    @Test
    void testResolveWithAnchor() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "#anchor";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file#anchor", resolvedUrl.toString());
    }

    @Test
    void testResolveWithDotSegments() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "./foo";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/foo", resolvedUrl.toString());
    }

    @Test
    void testResolveWithEmptyRelativeUrl() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file", resolvedUrl.toString());
    }

    @Test
    void testResolveWithAbsoluteUrl() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "http://another.com/path";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://another.com/path", resolvedUrl.toString());
    }

    @Test
    void testResolveWithInvalidBaseUrl() {
        URL base = null;
        String relUrl = "http://another.com/path";
        assertThrows(NullPointerException.class, () -> StringUtil.resolve(base, relUrl));
    }

    @Test
    void testResolveWithInvalidRelativeUrlThrowsException() {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "http://another.com/path\u0000";
        assertThrows(MalformedURLException.class, () -> StringUtil.resolve(base, relUrl));
    }
}
