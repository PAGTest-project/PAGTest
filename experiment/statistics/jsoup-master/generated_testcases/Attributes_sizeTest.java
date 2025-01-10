
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_sizeTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, attributes.size());
    }

    @Test
    public void testSizeAfterAdd() {
        attributes.put("key1", "value1");
        assertEquals(1, attributes.size());
    }

    @Test
    public void testSizeAfterRemove() {
        attributes.put("key1", "value1");
        attributes.remove("key1");
        assertEquals(0, attributes.size());
    }

    @Test
    public void testSizeAfterAddAll() {
        Attributes incoming = new Attributes();
        incoming.put("key1", "value1");
        incoming.put("key2", "value2");
        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
    }
}
