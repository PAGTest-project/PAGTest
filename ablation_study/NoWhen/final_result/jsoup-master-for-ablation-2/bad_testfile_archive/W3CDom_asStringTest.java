
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class W3CDom_asStringTest {

    @Test
    public void testAsString_WithPropertiesAndDoctype() throws Exception {
        // Given
        Document doc = W3CDom.convert(org.jsoup.Jsoup.parse("<!DOCTYPE html><html><head><title>Test</title></head><body><p>Hello, World!</p></body></html>"));
        Map<String, String> properties = new HashMap<>();
        properties.put("method", "html");

        // When
        String result = W3CDom.asString(doc, properties);

        // Then
        assertEquals("<!DOCTYPE html>\n<html>\n <head>\n  <title>Test</title>\n </head>\n <body>\n  <p>Hello, World!</p>\n </body>\n</html>", result);
    }

    @Test
    public void testAsString_WithDoctypeHtmlLegacy() throws Exception {
        // Given
        Document doc = W3CDom.convert(org.jsoup.Jsoup.parse("<!DOCTYPE html><html><head><title>Test</title></head><body><p>Hello, World!</p></body></html>"));

        // When
        String result = W3CDom.asString(doc, null);

        // Then
        assertEquals("<!DOCTYPE html SYSTEM \"about:legacy-compat\">\n<html>\n <head>\n  <title>Test</title>\n </head>\n <body>\n  <p>Hello, World!</p>\n </body>\n</html>", result);
    }

    @Test
    public void testAsString_TransformerException() {
        // Given
        Document doc = W3CDom.convert(org.jsoup.Jsoup.parse("<html><head><title>Test</title></head><body><p>Hello, World!</p></body></html>"));
        Map<String, String> properties = new HashMap<>();
        properties.put("invalidProperty", "invalidValue");

        // When & Then
        assertThrows(IllegalStateException.class, () -> W3CDom.asString(doc, properties));
    }
}
