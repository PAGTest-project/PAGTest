
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_putTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testPutNewKey() {
        attributes.put("key1", "value1");
        assertEquals("value1", attributes.get("key1"));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testPutExistingKey() {
        attributes.put("key1", "value1");
        attributes.put("key1", "newValue1");
        assertEquals("newValue1", attributes.get("key1"));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testPutNullValue() {
        attributes.put("key1", null);
        assertNull(attributes.get("key1"));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testPutNullKey() {
        assertThrows(IllegalArgumentException.class, () -> {
            attributes.put(null, "value1");
        });
    }

    @Test
    public void testPutEmptyKey() {
        attributes.put("", "value1");
        assertEquals("value1", attributes.get(""));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testPutMultipleKeys() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("key2"));
        assertEquals(2, attributes.size());
    }

    @Test
    public void testPutAndRemove() {
        attributes.put("key1", "value1");
        attributes.remove("key1");
        assertFalse(attributes.hasKey("key1"));
        assertEquals(0, attributes.size());
    }

    @Test
    public void testPutAndRemoveIgnoreCase() {
        attributes.put("Key1", "value1");
        attributes.removeIgnoreCase("key1");
        assertFalse(attributes.hasKeyIgnoreCase("Key1"));
        assertEquals(0, attributes.size());
    }

    @Test
    public void testPutAndSourceRange() {
        attributes.put("key1", "value1");
        Range.AttributeRange range = attributes.sourceRange("key1");
        assertNotNull(range);
    }

    @Test
    public void testPutAndHtml() {
        attributes.put("key1", "value1");
        String html = attributes.html();
        assertTrue(html.contains("key1=\"value1\""));
    }
}
