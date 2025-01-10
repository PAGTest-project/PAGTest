
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_deduplicateTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testDeduplicateWithPreserveCase() {
        ParseSettings settings = new ParseSettings(false, true);
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        attributes.put("key1", "value3");

        int dupes = attributes.deduplicate(settings);
        assertEquals(1, dupes);
        assertEquals(2, attributes.size());
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
    }

    @Test
    public void testDeduplicateWithoutPreserveCase() {
        ParseSettings settings = new ParseSettings(false, false);
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        attributes.put("KEY1", "value3");

        int dupes = attributes.deduplicate(settings);
        assertEquals(1, dupes);
        assertEquals(2, attributes.size());
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
    }

    @Test
    public void testDeduplicateEmptyAttributes() {
        ParseSettings settings = new ParseSettings(false, true);
        int dupes = attributes.deduplicate(settings);
        assertEquals(0, dupes);
        assertEquals(0, attributes.size());
    }

    @Test
    public void testDeduplicateNoDuplicates() {
        ParseSettings settings = new ParseSettings(false, true);
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");

        int dupes = attributes.deduplicate(settings);
        assertEquals(0, dupes);
        assertEquals(2, attributes.size());
        assertTrue(attributes.hasKey("key1"));
        assertTrue(attributes.hasKey("key2"));
    }
}
