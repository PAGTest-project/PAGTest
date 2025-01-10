
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.jsoup.nodes.Range.AttributeRange;
import java.util.HashMap;
import java.util.Map;

class Attributes_sourceRangeTest {

    @Test
    void testSourceRange() {
        // Given
        Attributes attributes = new Attributes();
        String key = "testKey";
        Map<String, AttributeRange> mockRanges = new HashMap<>();
        mockRanges.put(key, AttributeRange.UntrackedAttr);

        // Mocking the behavior of hasKey and getRanges
        Attributes spyAttributes = spy(attributes);
        doReturn(true).when(spyAttributes).hasKey(key);
        doReturn(mockRanges).when(spyAttributes).getRanges();

        // When
        AttributeRange result = spyAttributes.sourceRange(key);

        // Then
        assertEquals(AttributeRange.UntrackedAttr, result);
    }
}
