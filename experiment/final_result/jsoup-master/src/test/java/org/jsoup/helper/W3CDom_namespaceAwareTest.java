
package org.jsoup.helper;

import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class W3CDom_namespaceAwareTest {

    @Test
    public void testNamespaceAware() {
        W3CDom w3cDom = new W3CDom();

        // Given: Namespace awareness is initially true
        assertTrue(w3cDom.namespaceAware());

        // When: Setting namespace awareness to false
        w3cDom.namespaceAware(false);

        // Then: Namespace awareness should be false
        assertFalse(w3cDom.namespaceAware());

        // When: Setting namespace awareness back to true
        w3cDom.namespaceAware(true);

        // Then: Namespace awareness should be true
        assertTrue(w3cDom.namespaceAware());
    }
}
