
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
    public void testCloneAttributes() {
        attributes.add("Key1", "Val1")
                  .add("Key2", "Val2")
                  .add("Key3", null);

        Attributes clonedAttributes = attributes.clone();

        assertEquals(3, clonedAttributes.size());
        assertEquals("Val2", clonedAttributes.get("Key2"));
        assertEquals(attributes, clonedAttributes);

        clonedAttributes.add("Key4", "Val4");
        assertEquals(4, clonedAttributes.size());
        assertEquals(3, attributes.size());
        assertNotEquals(attributes, clonedAttributes);
    }

    @Test
    public void testCloneAttributesEmpty() {
        Attributes clonedAttributes = attributes.clone();

        assertEquals(0, clonedAttributes.size());
        assertEquals(attributes, clonedAttributes);
    }

    @Test
    public void testCloneAttributesWithNullValues() {
        attributes.add("Key1", null)
                  .add("Key2", "Val2")
                  .add("Key3", null);

        Attributes clonedAttributes = attributes.clone();

        assertEquals(3, clonedAttributes.size());
        assertNull(clonedAttributes.get("Key1"));
        assertEquals("Val2", clonedAttributes.get("Key2"));
        assertNull(clonedAttributes.get("Key3"));
        assertEquals(attributes, clonedAttributes);
    }
}
