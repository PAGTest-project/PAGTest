
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
    public void testHashCodeWithNormalizedKeys() {
        attributes.add("KEY1", "Val1");
        attributes.add("KEY2", "Val2");
        attributes.normalize();

        int hashCode1 = attributes.hashCode();

        attributes.add("KEY3", "Val3");
        attributes.normalize();

        int hashCode2 = attributes.hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void testHashCodeWithDeduplicatedKeys() {
        attributes.add("key1", "Val1");
        attributes.add("KEY1", "Val2");
        attributes.deduplicate(ParseSettings.preserveCase);

        int hashCode1 = attributes.hashCode();

        attributes.add("key2", "Val3");
        attributes.deduplicate(ParseSettings.preserveCase);

        int hashCode2 = attributes.hashCode();

        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    public void testHashCodeConsistencyWithEquals() {
        Attributes attributes1 = new Attributes();
        attributes1.add("key1", "Val1");
        attributes1.add("key2", "Val2");

        Attributes attributes2 = new Attributes();
        attributes2.add("key1", "Val1");
        attributes2.add("key2", "Val2");

        assertEquals(attributes1.hashCode(), attributes2.hashCode());
        assertEquals(attributes1, attributes2);

        attributes2.add("key3", "Val3");

        assertNotEquals(attributes1.hashCode(), attributes2.hashCode());
        assertNotEquals(attributes1, attributes2);
    }
}
