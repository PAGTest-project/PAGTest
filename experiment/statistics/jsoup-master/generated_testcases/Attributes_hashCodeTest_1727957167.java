
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_hashCodeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testHashCodeConsistency() {
        attributes.put("Key1", "Val1");
        attributes.put("Key2", "Val2");
        attributes.put("Key3", null);

        int initialHashCode = attributes.hashCode();

        // Modify the attributes
        attributes.put("Key4", "Val4");

        // Ensure hashCode changes after modification
        assertNotEquals(initialHashCode, attributes.hashCode());
    }

    @Test
    public void testHashCodeWithEmptyAttributes() {
        int hashCode = attributes.hashCode();
        assertEquals(0, hashCode);
    }

    @Test
    public void testHashCodeWithSingleAttribute() {
        attributes.put("Key1", "Val1");
        int hashCode = attributes.hashCode();
        assertNotEquals(0, hashCode);
    }

    @Test
    public void testHashCodeWithMultipleAttributes() {
        attributes.put("Key1", "Val1");
        attributes.put("Key2", "Val2");
        attributes.put("Key3", null);

        int hashCode = attributes.hashCode();
        assertNotEquals(0, hashCode);
    }
}
