
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
    public void testDeduplicate_EmptyAttributes() {
        ParseSettings settings = new ParseSettings(false, false);
        assertEquals(0, attributes.deduplicate(settings));
    }

    @Test
    public void testDeduplicate_NoDuplicates() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        ParseSettings settings = new ParseSettings(false, false);
        assertEquals(0, attributes.deduplicate(settings));
    }

    @Test
    public void testDeduplicate_CaseInsensitiveDuplicates() {
        attributes.put("key1", "value1");
        attributes.put("KEY1", "value2");
        ParseSettings settings = new ParseSettings(false, false);
        assertEquals(1, attributes.deduplicate(settings));
        assertEquals(1, attributes.size());
        assertEquals("value2", attributes.get("key1"));
    }

    @Test
    public void testDeduplicate_CaseSensitiveDuplicates() {
        attributes.put("key1", "value1");
        attributes.put("KEY1", "value2");
        ParseSettings settings = new ParseSettings(false, true);
        assertEquals(0, attributes.deduplicate(settings));
        assertEquals(2, attributes.size());
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("KEY1"));
    }

    @Test
    public void testDeduplicate_MixedCaseDuplicates() {
        attributes.put("key1", "value1");
        attributes.put("KEY1", "value2");
        attributes.put("key2", "value3");
        attributes.put("KEY2", "value4");
        ParseSettings settings = new ParseSettings(false, false);
        assertEquals(2, attributes.deduplicate(settings));
        assertEquals(2, attributes.size());
        assertEquals("value2", attributes.get("key1"));
        assertEquals("value4", attributes.get("key2"));
    }
}
