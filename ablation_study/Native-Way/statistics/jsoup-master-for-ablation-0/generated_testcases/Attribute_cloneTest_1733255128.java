
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attribute_cloneTest {
    private Attribute attribute;

    @BeforeEach
    public void setUp() {
        attribute = new Attribute("key", "value");
    }

    @Test
    public void testClone() {
        Attribute clonedAttribute = attribute.clone();
        assertNotSame(attribute, clonedAttribute);
        assertEquals(attribute, clonedAttribute);
    }

    @Test
    public void testCloneWithNullValue() {
        attribute = new Attribute("key", null);
        Attribute clonedAttribute = attribute.clone();
        assertNotSame(attribute, clonedAttribute);
        assertEquals(attribute, clonedAttribute);
    }

    @Test
    public void testCloneWithParent() {
        Attributes parent = new Attributes();
        attribute = new Attribute("key", "value", parent);
        Attribute clonedAttribute = attribute.clone();
        assertNotSame(attribute, clonedAttribute);
        assertEquals(attribute, clonedAttribute);
        assertNull(clonedAttribute.parent); // Parent should not be cloned
    }

    @Test
    public void testCloneWithBooleanAttribute() {
        attribute = new Attribute("checked", "");
        Attribute clonedAttribute = attribute.clone();
        assertNotSame(attribute, clonedAttribute);
        assertEquals(attribute, clonedAttribute);
    }
}
