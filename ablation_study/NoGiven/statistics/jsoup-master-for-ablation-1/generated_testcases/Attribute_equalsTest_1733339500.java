
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_equalsTest {
    private Attribute attribute1;
    private Attribute attribute2;

    @BeforeEach
    public void setUp() {
        attribute1 = new Attribute("key1", "value1");
        attribute2 = new Attribute("key2", "value2");
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
        assertFalse(attribute1.equals("not an Attribute"));
    }

    @Test
    public void testEqualsSameKeyAndValue() {
        Attribute sameAttribute = new Attribute("key1", "value1");
        assertTrue(attribute1.equals(sameAttribute));
    }

    @Test
    public void testEqualsDifferentKey() {
        Attribute differentKey = new Attribute("key3", "value1");
        assertFalse(attribute1.equals(differentKey));
    }

    @Test
    public void testEqualsDifferentValue() {
        Attribute differentValue = new Attribute("key1", "value3");
        assertFalse(attribute1.equals(differentValue));
    }

    @Test
    public void testEqualsDifferentKeyAndValue() {
        assertFalse(attribute1.equals(attribute2));
    }

    @Test
    public void testHashCodeConsistency() {
        Attribute sameAttribute = new Attribute("key1", "value1");
        assertEquals(attribute1.hashCode(), sameAttribute.hashCode());
    }

    @Test
    public void testHashCodeDifferentKey() {
        Attribute differentKey = new Attribute("key3", "value1");
        assertNotEquals(attribute1.hashCode(), differentKey.hashCode());
    }

    @Test
    public void testHashCodeDifferentValue() {
        Attribute differentValue = new Attribute("key1", "value3");
        assertNotEquals(attribute1.hashCode(), differentValue.hashCode());
    }

    @Test
    public void testHashCodeDifferentKeyAndValue() {
        assertNotEquals(attribute1.hashCode(), attribute2.hashCode());
    }
}
