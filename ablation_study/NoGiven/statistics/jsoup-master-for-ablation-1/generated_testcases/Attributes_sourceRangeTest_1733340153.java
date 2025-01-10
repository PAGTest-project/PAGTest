
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.HashMap;
import java.util.Map;

class Attributes_sourceRangeTest {

    @Test
    void testSourceRange() {
        // Given
        Attributes attributes = new Attributes();
        String key = "testKey";
        Range.AttributeRange expectedRange = new Range(new Position(1, 1, 1), new Position(2, 2, 2));
        Map<String, Range.AttributeRange> ranges = new HashMap<>();
        ranges.put(key, expectedRange);

        // Mocking hasKey and getRanges methods
        Attributes spyAttributes = spy(attributes);
        when(spyAttributes.hasKey(key)).thenReturn(true);
        when(spyAttributes.getRanges()).thenReturn(ranges);

        // When
        Range.AttributeRange result = spyAttributes.sourceRange(key);

        // Then
        assertEquals(expectedRange, result);
    }
}
