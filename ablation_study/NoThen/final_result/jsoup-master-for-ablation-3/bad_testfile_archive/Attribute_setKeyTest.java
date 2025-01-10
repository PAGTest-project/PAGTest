
package org.jsoup.nodes;

import org.jsoup.helper.Validate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

class Attribute_setKeyTest {

    @Test
    void testSetKey_NoParent() {
        Attribute attr = new Attribute("oldKey", "value", null);
        attr.setKey("newKey");
        assertEquals("newKey", attr.getKey());
    }

    @Test
    void testSetKey_WithParent() {
        Attributes parent = mock(Attributes.class);
        when(parent.indexOfKey("oldKey")).thenReturn(0);
        when(parent.keys).thenReturn(new String[]{"oldKey"});
        when(parent.getRanges()).thenReturn(null);

        Attribute attr = new Attribute("oldKey", "value", parent);
        attr.setKey("newKey");

        assertEquals("newKey", attr.getKey());
        verify(parent).indexOfKey("oldKey");
        verify(parent).keys[0] = "newKey";
    }

    @Test
    void testSetKey_WithParentAndRanges() {
        Attributes parent = mock(Attributes.class);
        when(parent.indexOfKey("oldKey")).thenReturn(0);
        when(parent.keys).thenReturn(new String[]{"oldKey"});
        Range.AttributeRange range = mock(Range.AttributeRange.class);
        Map<String, Range.AttributeRange> ranges = mock(Map.class);
        when(parent.getRanges()).thenReturn(ranges);
        when(ranges.remove("oldKey")).thenReturn(range);

        Attribute attr = new Attribute("oldKey", "value", parent);
        attr.setKey("newKey");

        assertEquals("newKey", attr.getKey());
        verify(parent).indexOfKey("oldKey");
        verify(parent).keys[0] = "newKey";
        verify(ranges).remove("oldKey");
        verify(ranges).put("newKey", range);
    }

    @Test
    void testSetKey_NullKey() {
        Attribute attr = new Attribute("oldKey", "value", null);
        assertThrows(IllegalArgumentException.class, () -> attr.setKey(null));
    }

    @Test
    void testSetKey_EmptyKey() {
        Attribute attr = new Attribute("oldKey", "value", null);
        assertThrows(IllegalArgumentException.class, () -> attr.setKey("   "));
    }
}
