
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
    public void testHashCodeWithEmptyAttributes() {
        int hashCode = attributes.hashCode();
        assertEquals(0, hashCode);
    }

    @Test
    public void testHashCodeWithSingleAttribute() {
        attributes.add("key1", "value1");
        int hashCode = attributes.hashCode();
        assertNotEquals(0, hashCode);
    }

    @Test
    public void testHashCodeWithMultipleAttributes() {
        attributes.add("key1", "value1");
        attributes.add("key2", "value2");
        int hashCode = attributes.hashCode();
        assertNotEquals(0, hashCode);
    }

    @Test
    public void testHashCodeAfterNormalize() {
        attributes.add("KEY1", "value1");
        attributes.add("KEY2", "value2");
        attributes.normalize();
        int hashCode = attributes.hashCode();
        assertNotEquals(0, hashCode);
    }

    @Test
    public void testHashCodeAfterDeduplicate() {
        attributes.add("key1", "value1");
        attributes.add("KEY1", "value1");
        attributes.deduplicate(ParseSettings.preserveCase);
        int hashCode = attributes.hashCode();
        assertNotEquals(0, hashCode);
    }

    @Test
    public void testHashCodeConsistencyWithEquals() {
        Attributes other = new Attributes();
        other.add("key1", "value1");
        other.add("key2", "value2");

        attributes.add("key1", "value1");
        attributes.add("key2", "value2");

        assertTrue(attributes.equals(other));
        assertEquals(attributes.hashCode(), other.hashCode());
    }

    @Test
    public void testHashCodeInconsistencyWithEquals() {
        Attributes other = new Attributes();
        other.add("key1", "value1");
        other.add("key2", "value2");

        attributes.add("key1", "value1");
        attributes.add("key2", "value3");

        assertFalse(attributes.equals(other));
        assertNotEquals(attributes.hashCode(), other.hashCode());
    }
}
