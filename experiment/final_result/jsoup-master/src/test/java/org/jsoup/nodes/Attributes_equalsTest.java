
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_equalsTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testEquals_SameObject() {
        assertTrue(attributes.equals(attributes));
    }

    @Test
    public void testEquals_NullObject() {
        assertFalse(attributes.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        Object differentClassObject = new Object();
        assertFalse(attributes.equals(differentClassObject));
    }

    @Test
    public void testEquals_DifferentSize() {
        Attributes other = new Attributes();
        other.put("key1", "value1");
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        assertFalse(attributes.equals(other));
    }

    @Test
    public void testEquals_SameKeysDifferentValues() {
        Attributes other = new Attributes();
        attributes.put("key1", "value1");
        other.put("key1", "value2");
        assertFalse(attributes.equals(other));
    }

    @Test
    public void testEquals_SameKeysSameValues() {
        Attributes other = new Attributes();
        attributes.put("key1", "value1");
        other.put("key1", "value1");
        assertTrue(attributes.equals(other));
    }

    @Test
    public void testEquals_DifferentKeys() {
        Attributes other = new Attributes();
        attributes.put("key1", "value1");
        other.put("key2", "value1");
        assertFalse(attributes.equals(other));
    }
}
