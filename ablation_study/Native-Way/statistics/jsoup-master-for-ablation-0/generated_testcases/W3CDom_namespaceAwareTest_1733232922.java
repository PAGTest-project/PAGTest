
package org.jsoup.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentBuilderFactory;

import static org.junit.jupiter.api.Assertions.*;

public class W3CDom_namespaceAwareTest {
    private W3CDom w3cDom;

    @BeforeEach
    public void setUp() {
        w3cDom = new W3CDom();
    }

    @Test
    public void testNamespaceAwareTrue() {
        W3CDom result = w3cDom.namespaceAware(true);
        assertTrue(w3cDom.namespaceAware());
        assertEquals(w3cDom, result);
    }

    @Test
    public void testNamespaceAwareFalse() {
        W3CDom result = w3cDom.namespaceAware(false);
        assertFalse(w3cDom.namespaceAware());
        assertEquals(w3cDom, result);
    }

    @Test
    public void testNamespaceAwareSettingAffectsFactory() {
        w3cDom.namespaceAware(true);
        assertTrue(w3cDom.factory.isNamespaceAware());

        w3cDom.namespaceAware(false);
        assertFalse(w3cDom.factory.isNamespaceAware());
    }
}
