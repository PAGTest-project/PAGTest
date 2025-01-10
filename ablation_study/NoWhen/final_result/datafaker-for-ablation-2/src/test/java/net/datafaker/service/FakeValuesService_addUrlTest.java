
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
    public void testAddUrlValid() throws MalformedURLException {
        Locale locale = Locale.US;
        URL url = new URL("https://example.com/valid.yml");
        fakeValuesService.addUrl(locale, url);
        // Additional assertions can be added here to verify the internal state changes
    }

    @Test
    public void testAddUrlNullLocale() throws MalformedURLException {
        Locale locale = null;
        URL url = new URL("https://example.com/valid.yml");
        assertThrows(NullPointerException.class, () -> fakeValuesService.addUrl(locale, url));
    }

    @Test
    public void testAddUrlNullUrl() {
        Locale locale = Locale.US;
        URL url = null;
        assertThrows(IllegalArgumentException.class, () -> fakeValuesService.addUrl(locale, url));
    }

    @Test
    public void testAddUrlInvalidUrl() {
        Locale locale = Locale.US;
        assertThrows(MalformedURLException.class, () -> fakeValuesService.addUrl(locale, new URL("invalid-url")));
    }
}
