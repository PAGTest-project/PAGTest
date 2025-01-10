
package org.jsoup.nodes;

import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_getValidKeyTest {

    @Test
    public void testGetValidKeyXmlValid() {
        String key = "valid_xml_key";
        String validKey = Attribute.getValidKey(key, Syntax.xml);
        assertEquals(key, validKey);
    }

    @Test
    public void testGetValidKeyXmlInvalid() {
        String key = "invalid xml key";
        String validKey = Attribute.getValidKey(key, Syntax.xml);
        assertEquals("invalid_xml_key", validKey);
    }

    @Test
    public void testGetValidKeyXmlCoerceFail() {
        String key = "invalid xml key with special characters like @#$%";
        String validKey = Attribute.getValidKey(key, Syntax.xml);
        assertNull(validKey);
    }

    @Test
    public void testGetValidKeyHtmlValid() {
        String key = "valid_html_key";
        String validKey = Attribute.getValidKey(key, Syntax.html);
        assertEquals(key, validKey);
    }

    @Test
    public void testGetValidKeyHtmlInvalid() {
        String key = "invalid html key";
        String validKey = Attribute.getValidKey(key, Syntax.html);
        assertEquals("invalid_html_key", validKey);
    }

    @Test
    public void testGetValidKeyHtmlCoerceFail() {
        String key = "invalid html key with special characters like @#$%";
        String validKey = Attribute.getValidKey(key, Syntax.html);
        assertNull(validKey);
    }
}
