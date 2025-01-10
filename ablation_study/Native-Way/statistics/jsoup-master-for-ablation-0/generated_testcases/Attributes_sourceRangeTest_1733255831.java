
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_sourceRangeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testSourceRangeWithExistingKey() {
        attributes.add("key1", "value1");
        attributes.add("key2", "value2");
        Map<String, Range.AttributeRange> ranges = new HashMap<>();
        ranges.put("key1", Range.AttributeRange.TrackedAttr);
        attributes.putUserData(AttrRangeKey, ranges);

        assertEquals(Range.AttributeRange.TrackedAttr, attributes.sourceRange("key1"));
    }

    @Test
    public void testSourceRangeWithNonExistingKey() {
        attributes.add("key1", "value1");
        attributes.add("key2", "value2");

        assertEquals(Range.AttributeRange.UntrackedAttr, attributes.sourceRange("key3"));
    }

    @Test
    public void testSourceRangeWithNullRanges() {
        assertEquals(Range.AttributeRange.UntrackedAttr, attributes.sourceRange("key1"));
    }

    @Test
    public void testSourceRangeWithEmptyAttributes() {
        assertEquals(Range.AttributeRange.UntrackedAttr, attributes.sourceRange("key1"));
    }
}
