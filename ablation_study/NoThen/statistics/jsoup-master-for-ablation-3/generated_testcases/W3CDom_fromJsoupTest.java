
package org.jsoup.helper;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType as W3CDocumentType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class W3CDom_fromJsoupTest {

    private W3CDom w3cDom;
    private DocumentBuilderFactory mockFactory;
    private DocumentBuilder mockBuilder;
    private DOMImplementation mockImpl;

    @BeforeEach
    void setUp() throws ParserConfigurationException {
        w3cDom = new W3CDom();
        mockFactory = mock(DocumentBuilderFactory.class);
        mockBuilder = mock(DocumentBuilder.class);
        mockImpl = mock(DOMImplementation.class);
        w3cDom.factory = mockFactory;
        when(mockFactory.newDocumentBuilder()).thenReturn(mockBuilder);
        when(mockBuilder.getDOMImplementation()).thenReturn(mockImpl);
    }

    @Test
    void testFromJsoup_withValidDocumentType() throws Exception {
        Document mockJsoupDoc = mock(Document.class);
        DocumentType mockDocType = mock(DocumentType.class);
        when(mockJsoupDoc.ownerDocument()).thenReturn(mockJsoupDoc);
        when(mockJsoupDoc.documentType()).thenReturn(mockDocType);
        when(mockDocType.name()).thenReturn("html");
        when(mockDocType.publicId()).thenReturn("-//W3C//DTD HTML 4.01//EN");
        when(mockDocType.systemId()).thenReturn("http://www.w3.org/TR/html4/strict.dtd");
        when(mockImpl.createDocumentType(anyString(), anyString(), anyString())).thenReturn(mock(W3CDocumentType.class));

        org.w3c.dom.Document result = w3cDom.fromJsoup(mockJsoupDoc);
        assertNotNull(result);
    }

    @Test
    void testFromJsoup_withNullElement() {
        assertThrows(IllegalArgumentException.class, () -> w3cDom.fromJsoup(null));
    }

    @Test
    void testFromJsoup_withParserConfigurationException() throws Exception {
        when(mockFactory.newDocumentBuilder()).thenThrow(ParserConfigurationException.class);
        assertThrows(IllegalStateException.class, () -> w3cDom.fromJsoup(mock(Element.class)));
    }
}
