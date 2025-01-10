
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_resolveTest {

    @Test
    public void testResolveWithRelativeUrl() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "../../../g";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/g", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithQueryParams() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "g?y/./x";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/b/c/g?y/./x", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithFragment() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "g#s/./x";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/b/c/g#s/./x", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithControlChars() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "g\u0000?y/./x";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/b/c/g?y/./x", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithEmptyRelativeUrl() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/b/c/d;p?q", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithAbsolutePath() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "/g";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/g", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithDifferentProtocol() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "https://example.com/g";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("https://example.com/g", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithDifferentHost() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "//example2.com/g";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example2.com/g", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithDifferentPort() throws MalformedURLException {
        URL base = new URL("http://example.com:8080/b/c/d;p?q");
        String relUrl = "./g";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com:8080/b/c/g", resolvedUrl.toExternalForm());
    }

    @Test
    public void testResolveWithDotSegments() throws MalformedURLException {
        URL base = new URL("http://example.com/b/c/d;p?q");
        String relUrl = "./../g";
        URL resolvedUrl = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/b/g", resolvedUrl.toExternalForm());
    }
}
