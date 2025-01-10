
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
    public void testNormalizeWithNoInternalKeys() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        attributes.normalize();
        assertEquals("key1", attributes.keys[0]);
        assertEquals("key2", attributes.keys[1]);
    }

    @Test
    public void testNormalizeWithInternalKeys() {
        attributes.put("/internalKey1", "value1");
        attributes.put("key2", "value2");
        attributes.normalize();
        assertEquals("/internalKey1", attributes.keys[0]);
        assertEquals("key2", attributes.keys[1]);
    }

    @Test
    public void testNormalizeWithMixedKeys() {
        attributes.put("/internalKey1", "value1");
        attributes.put("key2", "value2");
        attributes.put("key3", "value3");
        attributes.normalize();
        assertEquals("/internalKey1", attributes.keys[0]);
        assertEquals("key2", attributes.keys[1]);
        assertEquals("key3", attributes.keys[2]);
    }

    @Test
    public void testNormalizeWithAllInternalKeys() {
        attributes.put("/internalKey1", "value1");
        attributes.put("/internalKey2", "value2");
        attributes.normalize();
        assertEquals("/internalKey1", attributes.keys[0]);
        assertEquals("/internalKey2", attributes.keys[1]);
    }

    @Test
    public void testNormalizeWithEmptyAttributes() {
        attributes.normalize();
        assertEquals(0, attributes.size);
    }
}
