
package net.datafaker.service;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FakeValuesContext_hashCodeTest {

    @Test
    void testHashCode() throws Exception {
        // Given
        Locale locale = new Locale("en", "US");
        String filename = "testFile";
        String path = "testPath";
        URL url = new URL("http://example.com");

        FakeValuesContext context = FakeValuesContext.of(locale, filename, path, url);

        // When
        int hashCode = context.hashCode();

        // Then
        int expectedHashCode = 0;
        expectedHashCode = 31 * expectedHashCode + (locale == null ? 0 : locale.hashCode());
        expectedHashCode = 31 * expectedHashCode + filename.hashCode();
        expectedHashCode = 31 * expectedHashCode + path.hashCode();
        expectedHashCode = 31 * expectedHashCode + url.hashCode();

        assertEquals(expectedHashCode, hashCode);
    }
}
