
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_equalsTest {
    private Attributes attributes1;
    private Attributes attributes2;

    @BeforeEach
    public void setUp() {
        attributes1 = new Attributes();
        attributes2 = new Attributes();
    }

    @Test
    public void testEqualsWhenIdentical() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");

        attributes2.put("key1", "value1");
        attributes2.put("key2", "value2");

        assertTrue(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsWhenDifferentSize() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");

        attributes2.put("key1", "value1");

        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsWhenDifferentKeys() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");

        attributes2.put("key1", "value1");
        attributes2.put("key3", "value2");

        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsWhenDifferentValues() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");

        attributes2.put("key1", "value1");
        attributes2.put("key2", "value3");

        assertFalse(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsWhenOneIsNull() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");

        assertFalse(attributes1.equals(null));
    }

    @Test
    public void testEqualsWhenSameObject() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");

        assertTrue(attributes1.equals(attributes1));
    }

    @Test
    public void testEqualsWhenDifferentClass() {
        attributes1.put("key1", "value1");
        attributes1.put("key2", "value2");

        assertFalse(attributes1.equals("not an Attributes object"));
    }

    @Test
    public void testEqualsWhenNormalized() {
        attributes1.put("KEY1", "value1");
        attributes1.put("KEY2", "value2");
        attributes1.normalize();

        attributes2.put("key1", "value1");
        attributes2.put("key2", "value2");

        assertTrue(attributes1.equals(attributes2));
    }

    @Test
    public void testEqualsWhenDeduplicated() {
        attributes1.put("key1", "value1");
        attributes1.put("key1", "value1");
        attributes1.deduplicate(ParseSettings.preserveCase);

        attributes2.put("key1", "value1");

        assertTrue(attributes1.equals(attributes2));
    }
}
