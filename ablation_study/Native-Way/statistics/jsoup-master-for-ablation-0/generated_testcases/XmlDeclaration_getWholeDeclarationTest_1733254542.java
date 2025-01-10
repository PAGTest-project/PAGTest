
package org.jsoup.nodes;

import org.jsoup.SerializationException;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XmlDeclaration_getWholeDeclarationTest {

    @Test
    void testGetWholeDeclaration() {
        XmlDeclaration xmlDeclaration = new XmlDeclaration("name", false);
        xmlDeclaration.attr("key", "value");

        String result = xmlDeclaration.getWholeDeclaration();

        assertEquals(" key=\"value\"", result);
    }

    @Test
    void testGetWholeDeclaration_IOException() {
        XmlDeclaration xmlDeclaration = new XmlDeclaration("name", false);
        xmlDeclaration.attr("key", "value");

        // Mocking IOException by setting an invalid attribute key
        xmlDeclaration.attr(null, "value");

        assertThrows(SerializationException.class, xmlDeclaration::getWholeDeclaration);
    }
}
