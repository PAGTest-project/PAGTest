
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_hashCodeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
        attributes.add("Key1", "Val1");
        attributes.add("Key2", "Val2");
        attributes.add("Key3", null);
    }

    @Test
    public void testHashCodeConsistency() {
        Attributes clonedAttributes = attributes.clone();
        assertEquals(attributes.hashCode(), clonedAttributes.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        Attributes equalAttributes = new Attributes();
        equalAttributes.add("Key1", "Val1");
        equalAttributes.add("Key2", "Val2");
        equalAttributes.add("Key3", null);

        assertEquals(attributes.hashCode(), equalAttributes.hashCode());
    }

    @Test
    public void testHashCodeInequality() {
        Attributes differentAttributes = new Attributes();
        differentAttributes.add("Key1", "Val1");
        differentAttributes.add("Key2", "Val2");
        differentAttributes.add("Key3", "Val3");

        assertNotEquals(attributes.hashCode(), differentAttributes.hashCode());
    }
}
