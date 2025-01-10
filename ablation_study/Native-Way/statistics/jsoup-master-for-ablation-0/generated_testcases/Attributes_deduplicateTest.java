
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
    public void testDeduplicateNoDuplicates() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        ParseSettings settings = new ParseSettings(false, false);
        assertEquals(0, attributes.deduplicate(settings));
    }

    @Test
    public void testDeduplicateWithDuplicatesCaseInsensitive() {
        attributes.put("key1", "value1");
        attributes.put("KEY1", "value2");
        ParseSettings settings = new ParseSettings(false, false);
        assertEquals(1, attributes.deduplicate(settings));
        assertEquals(1, attributes.size());
    }

    @Test
    public void testDeduplicateWithDuplicatesCaseSensitive() {
        attributes.put("key1", "value1");
        attributes.put("KEY1", "value2");
        ParseSettings settings = new ParseSettings(false, true);
        assertEquals(0, attributes.deduplicate(settings));
        assertEquals(2, attributes.size());
    }

    @Test
    public void testDeduplicateEmptyAttributes() {
        ParseSettings settings = new ParseSettings(false, false);
        assertEquals(0, attributes.deduplicate(settings));
    }

    @Test
    public void testDeduplicateMixedCaseDuplicates() {
        attributes.put("key1", "value1");
        attributes.put("KEY1", "value2");
        attributes.put("key2", "value3");
        attributes.put("KEY2", "value4");
        ParseSettings settings = new ParseSettings(false, false);
        assertEquals(2, attributes.deduplicate(settings));
        assertEquals(2, attributes.size());
    }
}
