
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_addUrlTest {
    private FakeValuesService fakeValuesService;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
    }

    @Test
    public void testAddUrlSuccess() throws MalformedURLException {
        Locale locale = Locale.ENGLISH;
        URL url = new URL("http://example.com");
        assertDoesNotThrow(() -> fakeValuesService.addUrl(locale, url));
    }

    @Test
    public void testAddUrlNullUrl() {
        Locale locale = Locale.ENGLISH;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> fakeValuesService.addUrl(locale, null));
        assertEquals("url should be an existing readable file", exception.getMessage());
    }

    @Test
    public void testAddUrlNullLocale() {
        URL url = null;
        NullPointerException exception = assertThrows(NullPointerException.class,
                () -> fakeValuesService.addUrl(null, url));
        assertNotNull(exception);
    }
}
