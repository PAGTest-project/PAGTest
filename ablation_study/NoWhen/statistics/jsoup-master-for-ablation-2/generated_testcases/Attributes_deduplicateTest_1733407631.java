
package org.jsoup.nodes;

import org.jsoup.parser.ParseSettings;
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
        attributes.add("Key1", "Val1");
        attributes.add("Key2", "Val2");
        attributes.add("Key1", "Val1");

        ParseSettings settings = new ParseSettings(false, true);
        int dupes = attributes.deduplicate(settings);

        assertEquals(1, dupes);
        assertEquals(2, attributes.size());
        assertEquals("Val1", attributes.get("Key1"));
        assertEquals("Val2", attributes.get("Key2"));
    }

    @Test
    public void testDeduplicateWithoutPreserveCase() {
        attributes.add("Key1", "Val1");
        attributes.add("Key2", "Val2");
        attributes.add("key1", "Val1");

        ParseSettings settings = new ParseSettings(false, false);
        int dupes = attributes.deduplicate(settings);

        assertEquals(1, dupes);
        assertEquals(2, attributes.size());
        assertEquals("Val1", attributes.get("Key1"));
        assertEquals("Val2", attributes.get("Key2"));
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
        attributes.add("Key1", "Val1");
        attributes.add("Key2", "Val2");

        ParseSettings settings = new ParseSettings(false, true);
        int dupes = attributes.deduplicate(settings);

        assertEquals(0, dupes);
        assertEquals(2, attributes.size());
        assertEquals("Val1", attributes.get("Key1"));
        assertEquals("Val2", attributes.get("Key2"));
    }
}
