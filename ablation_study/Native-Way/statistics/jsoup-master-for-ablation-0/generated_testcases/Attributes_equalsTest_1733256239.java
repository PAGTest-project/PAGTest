
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_equalsTest {
    private Attributes attributes1;
    private Attributes attributes2;

    @BeforeEach
    public void setUp() {
        attributes1 = new Attributes();
        attributes2 = new Attributes();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(attributes1.equals(attributes1));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(attributes1.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        assertFalse(attributes1.equals("not an Attributes object"));
    }

    @Test
    public void testEquals_SameAttributes() {
        attributes1.put("key1", "value1");
        attributes2.put("key1", "value1");
        assertTrue(attributes1.equals(attributes2));
    }

    @Test
    public void testEquals_DifferentSize() {
        attributes1.put("key1", "value1");
        attributes2.put("key1", "value1");
        attributes2.put("key2", "value2");
        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEquals_DifferentKeys() {
        attributes1.put("key1", "value1");
        attributes2.put("key2", "value1");
        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEquals_DifferentValues() {
        attributes1.put("key1", "value1");
        attributes2.put("key1", "value2");
        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEquals_CaseInsensitiveKeys() {
        attributes1.put("key1", "value1");
        attributes2.put("KEY1", "value1");
        assertFalse(attributes1.equals(attributes2));
    }
}
