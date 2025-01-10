
package org.jsoup.nodes;

import org.jsoup.SerializationException;
import org.jsoup.internal.StringUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class XmlDeclaration_getWholeDeclarationTest {

    @Test
    public void testGetWholeDeclaration_Success() {
        // Given
        XmlDeclaration xmlDeclaration = new XmlDeclaration("name", false);
        xmlDeclaration.attr("key", "value");

        // When
        String result = xmlDeclaration.getWholeDeclaration();

        // Then
        assertEquals("name key=\"value\"", result);
    }

    @Test
    public void testGetWholeDeclaration_IOException() throws IOException {
        // Given
        XmlDeclaration xmlDeclaration = new XmlDeclaration("name", false);
        xmlDeclaration.attr("key", "value");

        XmlDeclaration spyXmlDeclaration = spy(xmlDeclaration);
        doThrow(new IOException("Mocked IOException")).when(spyXmlDeclaration).getWholeDeclaration(any(StringBuilder.class), any(Document.OutputSettings.class));

        // When & Then
        assertThrows(SerializationException.class, () -> spyXmlDeclaration.getWholeDeclaration());
    }
}
