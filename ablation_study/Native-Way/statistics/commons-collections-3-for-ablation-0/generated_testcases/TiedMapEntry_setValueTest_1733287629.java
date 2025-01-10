
package org.apache.commons.collections4.keyvalue;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiedMapEntry_setValueTest {

    private Map<String, String> map;
    private TiedMapEntry<String, String> entry;

    @BeforeEach
    public void setUp() {
        map = new HashMap<>();
        map.put("A", "a");
        map.put("B", "b");
        map.put("C", "c");
        entry = new TiedMapEntry<>(map, "A");
    }

    @Test
    public void testSetValueSuccess() {
        assertEquals("a", entry.setValue("x"));
        assertEquals("x", entry.getValue());
    }

    @Test
    public void testSetValueToSelf() {
        assertThrows(IllegalArgumentException.class, () -> entry.setValue(entry));
    }

    @Test
    public void testSetValueNull() {
        assertEquals("a", entry.setValue(null));
        assertNull(entry.getValue());
    }

    @Test
    public void testSetValueMultipleEntries() {
        TiedMapEntry<String, String> entryB = new TiedMapEntry<>(map, "B");
        TiedMapEntry<String, String> entryC = new TiedMapEntry<>(map, "C");

        assertEquals("b", entryB.setValue("y"));
        assertEquals("y", entryB.getValue());

        assertEquals("c", entryC.setValue("z"));
        assertEquals("z", entryC.getValue());
    }
}
