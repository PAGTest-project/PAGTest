
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
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
    public void testHashCodeWithSameAttributes() {
        Attributes sameAttributes = new Attributes();
        sameAttributes.add("Key1", "Val1");
        sameAttributes.add("Key2", "Val2");
        sameAttributes.add("Key3", null);

        assertEquals(attributes.hashCode(), sameAttributes.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentAttributes() {
        Attributes differentAttributes = new Attributes();
        differentAttributes.add("Key1", "Val1");
        differentAttributes.add("Key2", "Val2");
        differentAttributes.add("Key4", "Val4");

        assertNotEquals(attributes.hashCode(), differentAttributes.hashCode());
    }

    @Test
    public void testHashCodeWithEmptyAttributes() {
        Attributes emptyAttributes = new Attributes();

        assertEquals(0, emptyAttributes.hashCode());
    }

    @Test
    public void testHashCodeWithNullValues() {
        Attributes nullValueAttributes = new Attributes();
        nullValueAttributes.add("Key1", null);
        nullValueAttributes.add("Key2", "Val2");

        Attributes sameNullValueAttributes = new Attributes();
        sameNullValueAttributes.add("Key1", null);
        sameNullValueAttributes.add("Key2", "Val2");

        assertEquals(nullValueAttributes.hashCode(), sameNullValueAttributes.hashCode());
    }

    @Test
    public void testHashCodeWithDifferentOrder() {
        Attributes differentOrderAttributes = new Attributes();
        differentOrderAttributes.add("Key2", "Val2");
        differentOrderAttributes.add("Key1", "Val1");
        differentOrderAttributes.add("Key3", null);

        assertEquals(attributes.hashCode(), differentOrderAttributes.hashCode());
    }
}
