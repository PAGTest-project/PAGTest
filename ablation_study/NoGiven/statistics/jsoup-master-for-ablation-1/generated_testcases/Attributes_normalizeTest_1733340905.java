
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
        assertEquals("/internalKey", attributes.get("/internalKey"));
    }

    @Test
    public void testNormalizeWithNonInternalKey() {
        attributes.put("NonInternalKey", "value");
        attributes.normalize();
        assertEquals("noninternalkey", attributes.get("NonInternalKey"));
    }

    @Test
    public void testNormalizeWithMixedKeys() {
        attributes.put("/internalKey1", "value1");
        attributes.put("NonInternalKey2", "value2");
        attributes.normalize();
        assertEquals("/internalKey1", attributes.get("/internalKey1"));
        assertEquals("noninternalkey2", attributes.get("NonInternalKey2"));
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
