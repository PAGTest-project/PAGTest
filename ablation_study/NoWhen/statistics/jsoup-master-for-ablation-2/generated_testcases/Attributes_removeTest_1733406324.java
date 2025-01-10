
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_removeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
        attributes.put("Tot", "a&p");
        attributes.put("Hello", "There");
        attributes.put("data-name", "Jsoup");
    }

    @Test
    public void testRemoveExistingKey() {
        attributes.remove("Tot");
        assertEquals(2, attributes.size());
        assertFalse(attributes.hasKey("Tot"));
    }

    @Test
    public void testRemoveNonExistingKey() {
        attributes.remove("NonExistingKey");
        assertEquals(3, attributes.size());
    }

    @Test
    public void testRemoveIgnoreCaseExistingKey() {
        attributes.removeIgnoreCase("tot");
        assertEquals(2, attributes.size());
        assertFalse(attributes.hasKeyIgnoreCase("tot"));
    }

    @Test
    public void testRemoveIgnoreCaseNonExistingKey() {
        attributes.removeIgnoreCase("nonexistingkey");
        assertEquals(3, attributes.size());
    }
}
