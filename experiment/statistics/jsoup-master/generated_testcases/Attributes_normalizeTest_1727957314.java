
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
}
