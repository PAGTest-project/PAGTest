
package org.jsoup.nodes;

import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
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
        attributes.put("nonInternalKey", "VALUE");
        attributes.normalize();
        assertEquals("value", attributes.get("nonInternalKey"));
    }

    @Test
    public void testNormalizeWithMixedKeys() {
        attributes.put("/internalKey", "value");
        attributes.put("nonInternalKey", "VALUE");
        attributes.normalize();
        assertEquals("/internalKey", attributes.get("/internalKey"));
        assertEquals("value", attributes.get("nonInternalKey"));
    }

    @Test
    public void testNormalizeWithEmptyAttributes() {
        attributes.normalize();
        assertTrue(attributes.isEmpty());
    }

    @Test
    public void testNormalizeWithNullKey() {
        attributes.put(null, "value");
        assertThrows(AssertionError.class, () -> attributes.normalize());
    }

    @Test
    public void testNormalizeWithNullValue() {
        attributes.put("key", null);
        attributes.normalize();
        assertNull(attributes.get("key"));
    }

    @Test
    public void testNormalizeWithMultipleKeys() {
        attributes.put("key1", "VALUE1");
        attributes.put("key2", "VALUE2");
        attributes.put("key3", "VALUE3");
        attributes.normalize();
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("key2"));
        assertEquals("value3", attributes.get("key3"));
    }

    @Test
    public void testNormalizeWithDuplicateKeys() {
        attributes.put("key", "VALUE");
        attributes.put("key", "VALUE");
        attributes.normalize();
        assertEquals("value", attributes.get("key"));
    }

    @Test
    public void testNormalizeWithInternalAndNonInternalDuplicateKeys() {
        attributes.put("/internalKey", "value");
        attributes.put("internalKey", "VALUE");
        attributes.normalize();
        assertEquals("/internalKey", attributes.get("/internalKey"));
        assertEquals("value", attributes.get("internalKey"));
    }
}
