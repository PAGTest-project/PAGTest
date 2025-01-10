
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_equalsTest {

    @Test
    public void testEquals_SameObject() {
        Attribute attr = new Attribute("key", "value");
        assertTrue(attr.equals(attr));
    }

    @Test
    public void testEquals_NullObject() {
        Attribute attr = new Attribute("key", "value");
        assertFalse(attr.equals(null));
    }

    @Test
    public void testEquals_DifferentClass() {
        Attribute attr = new Attribute("key", "value");
        Object obj = new Object();
        assertFalse(attr.equals(obj));
    }

    @Test
    public void testEquals_DifferentKey() {
        Attribute attr1 = new Attribute("key1", "value");
        Attribute attr2 = new Attribute("key2", "value");
        assertFalse(attr1.equals(attr2));
    }

    @Test
    public void testEquals_DifferentValue() {
        Attribute attr1 = new Attribute("key", "value1");
        Attribute attr2 = new Attribute("key", "value2");
        assertFalse(attr1.equals(attr2));
    }

    @Test
    public void testEquals_SameKeyAndValue() {
        Attribute attr1 = new Attribute("key", "value");
        Attribute attr2 = new Attribute("key", "value");
        assertTrue(attr1.equals(attr2));
    }
}
