
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Objects;

public class Attribute_hashCodeTest {
    private Attribute attribute;

    @BeforeEach
    public void setUp() {
        attribute = new Attribute("key", "value");
    }

    @Test
    public void testHashCode() {
        int expectedHashCode = Objects.hash("key", "value");
        assertEquals(expectedHashCode, attribute.hashCode());
    }

    @Test
    public void testHashCodeWithNullValue() {
        attribute = new Attribute("key", null);
        int expectedHashCode = Objects.hash("key", null);
        assertEquals(expectedHashCode, attribute.hashCode());
    }
}
