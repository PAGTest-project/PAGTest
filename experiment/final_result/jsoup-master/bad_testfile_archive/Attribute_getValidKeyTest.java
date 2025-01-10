
package org.jsoup.nodes;

import org.jsoup.nodes.Document.OutputSettings.Syntax;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_getValidKeyTest {

    @Test
    void testGetValidKeyXml() {
        String validKey = "valid_key";
        String invalidKey = "invalid key";
        String coercedKey = "invalid_key";

        assertEquals(validKey, Attribute.getValidKey(validKey, Syntax.xml));
        assertEquals(coercedKey, Attribute.getValidKey(invalidKey, Syntax.xml));
        assertNull(Attribute.getValidKey("invalidkeywithspaces", Syntax.xml));
    }

    @Test
    void testGetValidKeyHtml() {
        String validKey = "valid_key";
        String invalidKey = "invalid key";
        String coercedKey = "invalid_key";

        assertEquals(validKey, Attribute.getValidKey(validKey, Syntax.html));
        assertEquals(coercedKey, Attribute.getValidKey(invalidKey, Syntax.html));
        assertNull(Attribute.getValidKey("invalidkeywithspaces", Syntax.html));
    }

    @Test
    void testGetValidKeyUnchanged() {
        String validKey = "valid_key";

        assertEquals(validKey, Attribute.getValidKey(validKey, Syntax.xml));
        assertEquals(validKey, Attribute.getValidKey(validKey, Syntax.html));
    }
}
