
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtil_resolveTest {

    @Test
    public void testResolve_NormalCase() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "?foo";
        URL resolved = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file?foo", resolved.toExternalForm());
    }

    @Test
    public void testResolve_WithRef() throws MalformedURLException {
        URL base = new URL("http://example.com/path/file");
        String relUrl = "?foo#bar";
        URL resolved = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/path/file?foo#bar", resolved.toExternalForm());
    }

    @Test
    public void testResolve_WithDotSegments() throws MalformedURLException {
        URL base = new URL("http://example.com/");
        String relUrl = "./foo";
        URL resolved = StringUtil.resolve(base, relUrl);
        assertEquals("http://example.com/foo", resolved.toExternalForm());
    }
}
