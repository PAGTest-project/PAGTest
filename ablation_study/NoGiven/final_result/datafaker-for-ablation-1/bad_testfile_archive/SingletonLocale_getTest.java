
package net.datafaker.internal.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class SingletonLocale_getTest {

    @BeforeEach
    void setUp() {
        SingletonLocale.LOCALE2SINGLETON_LOCALE.clear();
    }

    @Test
    void testGetWithNullLocale() {
        assertNull(SingletonLocale.get(null));
    }

    @Test
    void testGetWithNewLocale() {
        Locale locale = new Locale("en", "US");
        SingletonLocale result = SingletonLocale.get(locale);
        assertNotNull(result);
        assertEquals(locale, result.getLocale());
    }

    @Test
    void testGetWithExistingLocale() {
        Locale locale = new Locale("en", "US");
        SingletonLocale firstCall = SingletonLocale.get(locale);
        SingletonLocale secondCall = SingletonLocale.get(locale);
        assertNotNull(firstCall);
        assertNotNull(secondCall);
        assertSame(firstCall, secondCall);
    }
}
