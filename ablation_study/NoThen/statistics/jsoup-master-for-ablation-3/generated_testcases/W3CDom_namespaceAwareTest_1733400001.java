
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentBuilderFactory;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

public class W3CDom_namespaceAwareTest {

    @Test
    public void testNamespaceAwareTrue() {
        // Given
        W3CDom w3cDom = new W3CDom();
        DocumentBuilderFactory factory = Mockito.mock(DocumentBuilderFactory.class);
        w3cDom.factory = factory;

        // When
        w3cDom.namespaceAware(true);

        // Then
        assertTrue(w3cDom.namespaceAware());
        verify(factory).setNamespaceAware(true);
    }

    @Test
    public void testNamespaceAwareFalse() {
        // Given
        W3CDom w3cDom = new W3CDom();
        DocumentBuilderFactory factory = Mockito.mock(DocumentBuilderFactory.class);
        w3cDom.factory = factory;

        // When
        w3cDom.namespaceAware(false);

        // Then
        assertFalse(w3cDom.namespaceAware());
        verify(factory).setNamespaceAware(false);
    }
}
