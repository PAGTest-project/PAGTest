
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
    void testResolveWithInvalidRelativeUrl() {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "?foo\u0000";
        assertThrows(MalformedURLException.class, () -> StringUtil.resolve(base, relUrl));
    }

    @Test
    void testResolveWithControlCharsInBaseUrl() {
        String baseUrl = "http://example.com/path/file\u0000";
        String relUrl = "?foo";
        assertThrows(MalformedURLException.class, () -> StringUtil.resolve(new URL(baseUrl), relUrl));
    }

    @Test
    void testResolveWithNonAsciiChars() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "测试";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file/%E6%B5%8B%E8%AF%95", resolvedUrl.toString());
    }

    @Test
    void testResolveWithEmptyRelativeUrl() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file", resolvedUrl.toString());
    }

    @Test
    void testResolveWithEmptyBaseUrl() {
        URL base = null;
        String relUrl = "http://example.com/path/file";
        assertThrows(NullPointerException.class, () -> StringUtil.resolve(base, relUrl));
    }

    @Test
    void testResolveWithMalformedBaseUrl() {
        String baseUrl = "http://example.com/path/file\u0000";
        String relUrl = "?foo";
        assertThrows(MalformedURLException.class, () -> StringUtil.resolve(new URL(baseUrl), relUrl));
    }

    @Test
    void testResolveWithMalformedRelativeUrl() {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "?foo\u0000";
        assertThrows(MalformedURLException.class, () -> StringUtil.resolve(base, relUrl));
    }
}
