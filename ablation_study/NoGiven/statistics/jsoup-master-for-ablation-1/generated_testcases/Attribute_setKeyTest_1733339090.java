
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
        attribute = new Attribute("oldKey", "value", parent);
        parent.put("oldKey", "value");
    }

    @Test
    public void testSetKeyValid() {
        attribute.setKey("newKey");
        assertEquals("newKey", attribute.getKey());
        assertEquals("value", parent.get("newKey"));
        assertNull(parent.get("oldKey"));
    }

    @Test
    public void testSetKeyNull() {
        assertThrows(IllegalArgumentException.class, () -> attribute.setKey(null));
    }

    @Test
    public void testSetKeyEmpty() {
        assertThrows(IllegalArgumentException.class, () -> attribute.setKey(""));
    }

    @Test
    public void testSetKeyWithSpaces() {
        attribute.setKey(" newKey ");
        assertEquals("newKey", attribute.getKey());
        assertEquals("value", parent.get("newKey"));
        assertNull(parent.get("oldKey"));
    }

    @Test
    public void testSetKeyNoParent() {
        attribute = new Attribute("oldKey", "value");
        attribute.setKey("newKey");
        assertEquals("newKey", attribute.getKey());
    }

    @Test
    public void testSetKeyUpdateRanges() {
        Map<String, Range.AttributeRange> ranges = parent.getRanges();
        ranges.put("oldKey", new Range.AttributeRange(new Range(new Position(0, 0, 0), new Position(0, 0, 0))));

        attribute.setKey("newKey");
        assertEquals("newKey", attribute.getKey());
        assertEquals("value", parent.get("newKey"));
        assertNull(parent.get("oldKey"));
        assertNotNull(ranges.get("newKey"));
        assertNull(ranges.get("oldKey"));
    }
}
