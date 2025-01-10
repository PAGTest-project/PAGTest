
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_equalsTest {
    private Attribute attribute1;
    private Attribute attribute2;
    private Attribute attribute3;

    @BeforeEach
    public void setUp() {
        attribute1 = new Attribute("key1", "value1");
        attribute2 = new Attribute("key1", "value1");
        attribute3 = new Attribute("key2", "value2");
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(attribute1.equals(attribute1));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(attribute1.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(attribute1.equals("not an attribute"));
    }

    @Test
    public void testEqualsSameKeyAndValue() {
        assertTrue(attribute1.equals(attribute2));
    }

    @Test
    public void testEqualsDifferentKey() {
        assertFalse(attribute1.equals(attribute3));
    }

    @Test
    public void testEqualsDifferentValue() {
        Attribute attribute4 = new Attribute("key1", "value2");
        assertFalse(attribute1.equals(attribute4));
    }

    @Test
    public void testEqualsNullValue() {
        Attribute attribute5 = new Attribute("key1", null);
        Attribute attribute6 = new Attribute("key1", null);
        assertTrue(attribute5.equals(attribute6));
        assertFalse(attribute1.equals(attribute5));
    }
}
