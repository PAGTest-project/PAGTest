
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_getIgnoreCaseTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
        attributes.put("a", "valueA");
        attributes.put("B", "valueB");
    }

    @Test
    public void testGetIgnoreCase_KeyExists() {
        assertEquals("valueA", attributes.getIgnoreCase("a"));
        assertEquals("valueA", attributes.getIgnoreCase("A"));
        assertEquals("valueB", attributes.getIgnoreCase("b"));
        assertEquals("valueB", attributes.getIgnoreCase("B"));
    }

    @Test
    public void testGetIgnoreCase_KeyDoesNotExist() {
        assertEquals("", attributes.getIgnoreCase("c"));
        assertEquals("", attributes.getIgnoreCase("C"));
    }
}
