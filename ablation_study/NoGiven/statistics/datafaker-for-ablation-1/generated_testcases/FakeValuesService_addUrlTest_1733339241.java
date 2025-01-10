
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void testAddUrlValid() throws Exception {
        Locale locale = Locale.US;
        URL url = new URL("https://example.com/valid.yml");
        fakeValuesService.addUrl(locale, url);
        // Additional assertions can be added here to verify the state or behavior
    }

    @Test
    public void testAddUrlNullUrl() {
        Locale locale = Locale.US;
        assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.addUrl(locale, null);
        });
    }

    @Test
    public void testAddUrlNullLocale() {
        URL url = new URL("https://example.com/valid.yml");
        assertThrows(NullPointerException.class, () -> {
            fakeValuesService.addUrl(null, url);
        });
    }

    @Test
    public void testAddUrlInvalidUrl() {
        Locale locale = Locale.US;
        assertThrows(MalformedURLException.class, () -> {
            fakeValuesService.addUrl(locale, new URL("invalid-url"));
        });
    }
}
