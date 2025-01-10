
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
        URL url = new URL("https://example.com");
        fakeValuesService.addUrl(locale, url);
        // Additional assertions can be added here to verify the state of fakeValuesInterfaceMap
    }

    @Test
    public void testAddUrlNullLocale() {
        Locale locale = null;
        URL url = null;
        assertThrows(NullPointerException.class, () -> {
            fakeValuesService.addUrl(locale, url);
        });
    }

    @Test
    public void testAddUrlNullUrl() {
        Locale locale = Locale.US;
        URL url = null;
        assertThrows(IllegalArgumentException.class, () -> {
            fakeValuesService.addUrl(locale, url);
        });
    }
}
