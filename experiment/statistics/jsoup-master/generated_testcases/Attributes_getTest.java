
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_getTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testGetExistingKey() {
        attributes.put("key1", "value1");
        assertEquals("value1", attributes.get("key1"));
    }

    @Test
    public void testGetNonExistingKey() {
        assertEquals("", attributes.get("nonExistingKey"));
    }
}
