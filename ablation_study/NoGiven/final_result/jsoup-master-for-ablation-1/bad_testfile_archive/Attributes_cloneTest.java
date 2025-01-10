
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
        assertTrue(cloned.equals(attributes));
        assertEquals(attributes.html(), cloned.html());
    }

    @Test
    public void testCloneWithSingleAttribute() {
        attributes.put("key1", "value1");
        Attributes cloned = attributes.clone();
        assertTrue(cloned.equals(attributes));
        assertEquals(attributes.html(), cloned.html());
    }

    @Test
    public void testCloneWithMultipleAttributes() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        Attributes cloned = attributes.clone();
        assertTrue(cloned.equals(attributes));
        assertEquals(attributes.html(), cloned.html());
    }

    @Test
    public void testCloneAfterAddingAll() {
        Attributes otherAttributes = new Attributes();
        otherAttributes.put("key1", "value1");
        otherAttributes.put("key2", "value2");
        attributes.addAll(otherAttributes);

        Attributes cloned = attributes.clone();
        assertTrue(cloned.equals(attributes));
        assertEquals(attributes.html(), cloned.html());
    }

    @Test
    public void testCloneAfterPutAttribute() {
        attributes.put(new Attribute("key1", "value1"));
        Attributes cloned = attributes.clone();
        assertTrue(cloned.equals(attributes));
        assertEquals(attributes.html(), cloned.html());
    }
}
