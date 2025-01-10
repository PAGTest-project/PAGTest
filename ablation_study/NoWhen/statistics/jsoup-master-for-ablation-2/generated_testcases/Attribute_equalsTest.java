
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import org.jspecify.annotations.Nullable;

import static org.junit.jupiter.api.Assertions.*;

class Attribute_equalsTest {

    @Test
    void testEquals_SameObject() {
        Attribute attr = new Attribute("key", "value");
        assertTrue(attr.equals(attr));
    }

    @Test
    void testEquals_NullObject() {
        Attribute attr = new Attribute("key", "value");
        assertFalse(attr.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        Attribute attr = new Attribute("key", "value");
        Object obj = new Object();
        assertFalse(attr.equals(obj));
    }

    @Test
    void testEquals_DifferentKey() {
        Attribute attr1 = new Attribute("key1", "value");
        Attribute attr2 = new Attribute("key2", "value");
        assertFalse(attr1.equals(attr2));
    }

    @Test
    void testEquals_DifferentValue() {
        Attribute attr1 = new Attribute("key", "value1");
        Attribute attr2 = new Attribute("key", "value2");
        assertFalse(attr1.equals(attr2));
    }

    @Test
    void testEquals_SameKeyAndValue() {
        Attribute attr1 = new Attribute("key", "value");
        Attribute attr2 = new Attribute("key", "value");
        assertTrue(attr1.equals(attr2));
    }

    @Test
    void testEquals_NullValue() {
        Attribute attr1 = new Attribute("key", null);
        Attribute attr2 = new Attribute("key", null);
        assertTrue(attr1.equals(attr2));
    }

    @Test
    void testEquals_OneNullValue() {
        Attribute attr1 = new Attribute("key", "value");
        Attribute attr2 = new Attribute("key", null);
        assertFalse(attr1.equals(attr2));
    }
}
