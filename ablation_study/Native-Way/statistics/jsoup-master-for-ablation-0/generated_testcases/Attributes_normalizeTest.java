
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_normalizeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testNormalizeWithInternalKey() {
        attributes.put("/internalKey", "value");
        attributes.normalize();
        assertEquals(1, attributes.size());
        assertEquals("/internalKey", attributes.get("/internalKey"));
    }

    @Test
    public void testNormalizeWithNonInternalKey() {
        attributes.put("NonInternalKey", "value");
        attributes.normalize();
        assertEquals(1, attributes.size());
        assertEquals("noninternalkey", attributes.get("NonInternalKey"));
    }

    @Test
    public void testNormalizeWithMixedKeys() {
        attributes.put("/internalKey", "value");
        attributes.put("NonInternalKey", "value");
        attributes.normalize();
        assertEquals(2, attributes.size());
        assertEquals("/internalKey", attributes.get("/internalKey"));
        assertEquals("noninternalkey", attributes.get("NonInternalKey"));
    }

    @Test
    public void testNormalizeWithEmptyAttributes() {
        attributes.normalize();
        assertEquals(0, attributes.size());
    }

    @Test
    public void testNormalizeWithNullKey() {
        attributes.put(null, "value");
        assertThrows(AssertionError.class, () -> attributes.normalize());
    }
}
