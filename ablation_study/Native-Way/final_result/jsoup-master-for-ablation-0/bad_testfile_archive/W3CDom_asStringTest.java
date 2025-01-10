
package org.jsoup.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.DOMImplementation;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class W3CDom_asStringTest {

    private DocumentBuilder builder;

    @BeforeEach
    public void setUp() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }

    @Test
    public void testAsStringWithNullProperties() {
        Document doc = builder.newDocument();
        String result = W3CDom.asString(doc, null);
        assertEquals("", result);
    }

    @Test
    public void testAsStringWithProperties() {
        Document doc = builder.newDocument();
        Map<String, String> properties = new HashMap<>();
        properties.put("indent", "yes");
        String result = W3CDom.asString(doc, properties);
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n", result);
    }

    @Test
    public void testAsStringWithDoctypePublicId() {
        Document doc = builder.newDocument();
        DOMImplementation domImpl = doc.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("html", "-//W3C//DTD HTML 4.01//EN", "http://www.w3.org/TR/html4/strict.dtd");
        doc.appendChild(doctype);
        String result = W3CDom.asString(doc, null);
        assertEquals("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">\n", result);
    }

    @Test
    public void testAsStringWithDoctypeSystemId() {
        Document doc = builder.newDocument();
        DOMImplementation domImpl = doc.getImplementation();
        DocumentType doctype = domImpl.createDocumentType("html", null, "about:legacy-compat");
        doc.appendChild(doctype);
        String result = W3CDom.asString(doc, null);
        assertEquals("<!DOCTYPE html SYSTEM \"about:legacy-compat\">\n", result);
    }

    @Test
    public void testAsStringWithTransformerException() {
        Document doc = builder.newDocument();
        Map<String, String> properties = new HashMap<>();
        properties.put("invalidProperty", "invalidValue");
        assertThrows(IllegalStateException.class, () -> W3CDom.asString(doc, properties));
    }
}
