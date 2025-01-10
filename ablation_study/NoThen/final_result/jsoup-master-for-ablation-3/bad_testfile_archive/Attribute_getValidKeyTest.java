
package org.jsoup.nodes;

import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Attribute_getValidKeyTest {

    @Test
    void testGetValidKey_XmlSyntax_ValidKey() {
        String key = "validKey";
        String result = Document.getValidKey(key, Syntax.xml);
        assertEquals(key, result);
    }

    @Test
    void testGetValidKey_XmlSyntax_InvalidKey_Coerced() {
        String key = "invalid-key!";
        String result = Document.getValidKey(key, Syntax.xml);
        assertEquals("invalid_key_", result);
    }

    @Test
    void testGetValidKey_XmlSyntax_InvalidKey_CoercedToNull() {
        String key = "!@#$%";
        String result = Document.getValidKey(key, Syntax.xml);
        assertNull(result);
    }

    @Test
    void testGetValidKey_HtmlSyntax_ValidKey() {
        String key = "validKey";
        String result = Document.getValidKey(key, Syntax.html);
        assertEquals(key, result);
    }

    @Test
    void testGetValidKey_HtmlSyntax_InvalidKey_Coerced() {
        String key = "invalid key!";
        String result = Document.getValidKey(key, Syntax.html);
        assertEquals("invalid_key_", result);
    }

    @Test
    void testGetValidKey_HtmlSyntax_InvalidKey_CoercedToNull() {
        String key = "!@#$%";
        String result = Document.getValidKey(key, Syntax.html);
        assertNull(result);
    }
}
