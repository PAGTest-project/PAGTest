
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_cloneTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testCloneWithEmptyAttributes() {
        Attributes cloned = attributes.clone();
        assertEquals(attributes, cloned);
        assertEquals(attributes.hashCode(), cloned.hashCode());
    }

    @Test
    public void testCloneWithSingleAttribute() {
        attributes.put("Key1", "Val1");
        Attributes cloned = attributes.clone();
        assertEquals(attributes, cloned);
        assertEquals(attributes.hashCode(), cloned.hashCode());
    }

    @Test
    public void testCloneWithMultipleAttributes() {
        attributes.put("Key1", "Val1");
        attributes.put("Key2", "Val2");
        attributes.put("Key3", null);
        Attributes cloned = attributes.clone();
        assertEquals(attributes, cloned);
        assertEquals(attributes.hashCode(), cloned.hashCode());
    }

    @Test
    public void testCloneWithAttributesAddedViaAddAll() {
        Attributes incoming = new Attributes();
        incoming.put("Key1", "Val1");
        incoming.put("Key2", "Val2");
        attributes.addAll(incoming);
        Attributes cloned = attributes.clone();
        assertEquals(attributes, cloned);
        assertEquals(attributes.hashCode(), cloned.hashCode());
    }

    @Test
    public void testCloneWithAttributesAddedViaPutAttribute() {
        Attribute attr1 = new Attribute("Key1", "Val1");
        Attribute attr2 = new Attribute("Key2", "Val2");
        attributes.put(attr1);
        attributes.put(attr2);
        Attributes cloned = attributes.clone();
        assertEquals(attributes, cloned);
        assertEquals(attributes.hashCode(), cloned.hashCode());
    }
}
