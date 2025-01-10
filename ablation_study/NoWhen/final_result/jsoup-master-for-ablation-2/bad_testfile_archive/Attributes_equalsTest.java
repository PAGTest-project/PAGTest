
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
    public void testEqualsSameObject() {
        assertTrue(attributes1.equals(attributes1));
    }

    @Test
    public void testEqualsNullObject() {
        assertFalse(attributes1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(attributes1.equals("not an Attributes object"));
    }

    @Test
    public void testEqualsDifferentSize() {
        attributes1.put("key1", "value1");
        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsSameKeysDifferentValues() {
        attributes1.put("key1", "value1");
        attributes2.put("key1", "value2");
        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsSameKeysSameValues() {
        attributes1.put("key1", "value1");
        attributes2.put("key1", "value1");
        assertTrue(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsDifferentKeysSameValues() {
        attributes1.put("key1", "value1");
        attributes2.put("key2", "value1");
        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsMultipleKeysSameValues() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");
        attributes2.put("key1", "value1");
        attributes2.put("key2", "value2");
        assertTrue(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsMultipleKeysDifferentValues() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");
        attributes2.put("key1", "value1");
        attributes2.put("key2", "value3");
        assertFalse(attributes1.equals(attributes2));
    }
}
