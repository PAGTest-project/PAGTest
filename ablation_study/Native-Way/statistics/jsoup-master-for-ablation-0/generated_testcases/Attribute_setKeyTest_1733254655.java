
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_setKeyTest {
    private Attribute attribute;
    private Attributes parent;

    @BeforeEach
    public void setUp() {
        parent = new Attributes();
        attribute = new Attribute("oldKey", "oldValue", parent);
    }

    @Test
    public void testSetKeyWithValidKey() {
        attribute.setKey("newKey");
        assertEquals("newKey", attribute.getKey());
        assertEquals("oldValue", parent.get("newKey"));
        assertFalse(parent.hasKey("oldKey"));
    }

    @Test
    public void testSetKeyWithEmptyParent() {
        attribute = new Attribute("oldKey", "oldValue", null);
        attribute.setKey("newKey");
        assertEquals("newKey", attribute.getKey());
    }

    @Test
    public void testSetKeyWithKeyNotFoundInParent() {
        parent.remove("oldKey");
        attribute.setKey("newKey");
        assertEquals("newKey", attribute.getKey());
        assertFalse(parent.hasKey("newKey"));
    }

    @Test
    public void testSetKeyWithRangeTracking() {
        parent.put("oldKey", "oldValue");
        Map<String, Range.AttributeRange> ranges = parent.getRanges();
        ranges.put("oldKey", new Range.AttributeRange(new Range.Position(0, 0, 0), new Range.Position(0, 0, 0)));

        attribute.setKey("newKey");
        assertEquals("newKey", attribute.getKey());
        assertEquals("oldValue", parent.get("newKey"));
        assertFalse(parent.hasKey("oldKey"));
        assertNotNull(ranges.get("newKey"));
        assertNull(ranges.get("oldKey"));
    }

    @Test
    public void testSetKeyWithNullKey() {
        assertThrows(IllegalArgumentException.class, () -> attribute.setKey(null));
    }

    @Test
    public void testSetKeyWithEmptyKey() {
        assertThrows(IllegalArgumentException.class, () -> attribute.setKey(""));
    }

    @Test
    public void testSetKeyWithWhitespaceKey() {
        attribute.setKey(" newKey ");
        assertEquals("newKey", attribute.getKey());
    }
}
