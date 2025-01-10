
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_asListTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testAsListWithNormalKeys() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");

        List<Attribute> list = attributes.asList();
        assertEquals(2, list.size());
        assertEquals("key1", list.get(0).getKey());
        assertEquals("value1", list.get(0).getValue());
        assertEquals("key2", list.get(1).getKey());
        assertEquals("value2", list.get(1).getValue());
    }

    @Test
    public void testAsListWithInternalKeys() {
        attributes.put("/internalKey1", "value1");
        attributes.put("key2", "value2");

        List<Attribute> list = attributes.asList();
        assertEquals(1, list.size());
        assertEquals("key2", list.get(0).getKey());
        assertEquals("value2", list.get(0).getValue());
    }

    @Test
    public void testAsListWithEmptyAttributes() {
        List<Attribute> list = attributes.asList();
        assertTrue(list.isEmpty());
    }
}
