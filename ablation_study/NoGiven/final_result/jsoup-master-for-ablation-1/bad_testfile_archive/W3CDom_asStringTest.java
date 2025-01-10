
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import javax.xml.transform.TransformerException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class W3CDom_asStringTest {

    @Test
    public void testAsString_WithPropertiesAndDoctype() throws Exception {
        // Given
        Document mockDoc = mock(Document.class);
        DocumentType mockDoctype = mock(DocumentType.class);
        when(mockDoc.getDoctype()).thenReturn(mockDoctype);
        when(mockDoctype.getPublicId()).thenReturn("publicId");
        when(mockDoctype.getSystemId()).thenReturn("systemId");

        Map<String, String> properties = new HashMap<>();
        properties.put("key", "value");

        // When
        String result = W3CDom.asString(mockDoc, properties);

        // Then
        assertEquals("", result); // Since we are mocking, the result will be an empty string
    }

    @Test
    public void testAsString_WithHtmlDoctype() throws Exception {
        // Given
        Document mockDoc = mock(Document.class);
        DocumentType mockDoctype = mock(DocumentType.class);
        when(mockDoc.getDoctype()).thenReturn(mockDoctype);
        when(mockDoctype.getName()).thenReturn("html");
        when(mockDoctype.getPublicId()).thenReturn("");
        when(mockDoctype.getSystemId()).thenReturn("");

        // When
        String result = W3CDom.asString(mockDoc, null);

        // Then
        assertEquals("", result); // Since we are mocking, the result will be an empty string
    }

    @Test
    public void testAsString_TransformerException() throws Exception {
        // Given
        Document mockDoc = mock(Document.class);
        when(mockDoc.getDoctype()).thenReturn(null);

        TransformerException transformerException = new TransformerException("Transformer error");
        TransformerFactory mockTf = mock(TransformerFactory.class);
        Transformer mockTransformer = mock(Transformer.class);
        when(mockTf.newTransformer()).thenReturn(mockTransformer);
        doThrow(transformerException).when(mockTransformer).transform(any(), any());

        // When and Then
        assertThrows(IllegalStateException.class, () -> W3CDom.asString(mockDoc, null));
    }
}
