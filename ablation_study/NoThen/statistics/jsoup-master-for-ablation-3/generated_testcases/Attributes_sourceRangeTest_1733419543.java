
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

public class Attributes_sourceRangeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testSourceRangeWithExistingKey() {
        // Given
        Map<String, Range.AttributeRange> ranges = new HashMap<>();
        ranges.put("key1", Range.AttributeRange.TrackedAttr);
        attributes.userData().put(SharedConstants.AttrRangeKey, ranges);

        // When
        Range.AttributeRange result = attributes.sourceRange("key1");

        // Then
        assertEquals(Range.AttributeRange.TrackedAttr, result);
    }

    @Test
    public void testSourceRangeWithNonExistingKey() {
        // Given
        Map<String, Range.AttributeRange> ranges = new HashMap<>();
        ranges.put("key1", Range.AttributeRange.TrackedAttr);
        attributes.userData().put(SharedConstants.AttrRangeKey, ranges);

        // When
        Range.AttributeRange result = attributes.sourceRange("key2");

        // Then
        assertEquals(Range.AttributeRange.UntrackedAttr, result);
    }

    @Test
    public void testSourceRangeWithNullRanges() {
        // Given
        attributes.userData().put(SharedConstants.AttrRangeKey, null);

        // When
        Range.AttributeRange result = attributes.sourceRange("key1");

        // Then
        assertEquals(Range.AttributeRange.UntrackedAttr, result);
    }

    @Test
    public void testSourceRangeWithNoUserData() {
        // Given
        // No user data added

        // When
        Range.AttributeRange result = attributes.sourceRange("key1");

        // Then
        assertEquals(Range.AttributeRange.UntrackedAttr, result);
    }
}
