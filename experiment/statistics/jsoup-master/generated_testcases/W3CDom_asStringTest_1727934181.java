
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class W3CDom_asStringTest {

    @Test
    public void testAsString_withPropertiesAndDoctype() throws ParserConfigurationException {
        // Given
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();
        DocumentType doctype = doc.getImplementation().createDocumentType("html", "", "about:legacy-compat");
        doc.appendChild(doctype);

        Map<String, String> properties = new HashMap<>();
        properties.put("method", "html");

        // When
        String result = W3CDom.asString(doc, properties);

        // Then
        assertEquals("<!DOCTYPE html SYSTEM \"about:legacy-compat\">", result.trim());
    }

    @Test
    public void testAsString_transformerException() throws ParserConfigurationException {
        // Given
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        // Mocking a TransformerException by passing invalid properties
        Map<String, String> properties = new HashMap<>();
        properties.put("invalidProperty", "invalidValue");

        // When and Then
        assertThrows(IllegalStateException.class, () -> W3CDom.asString(doc, properties));
    }
}
