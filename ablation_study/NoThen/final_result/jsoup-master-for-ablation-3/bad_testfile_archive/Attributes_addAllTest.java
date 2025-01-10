
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Attributes_addAllTest {

    @Test
    void testAddAll_EmptyIncoming() {
        Attributes attributes = new Attributes();
        Attributes incoming = new Attributes();
        attributes.addAll(incoming);
        assertEquals(0, attributes.size());
    }

    @Test
    void testAddAll_NonEmptyIncoming() {
        Attributes attributes = new Attributes();
        Attributes incoming = new Attributes();
        incoming.put("key1", "value1");
        incoming.put("key2", "value2");
        attributes.addAll(incoming);
        assertEquals(2, attributes.size());
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("key2"));
    }

    @Test
    void testAddAll_NonEmptyIncomingWithExistingAttributes() {
        Attributes attributes = new Attributes();
        attributes.put("key0", "value0");
        Attributes incoming = new Attributes();
        incoming.put("key1", "value1");
        incoming.put("key2", "value2");
        attributes.addAll(incoming);
        assertEquals(3, attributes.size());
        assertEquals("value0", attributes.get("key0"));
        assertEquals("value1", attributes.get("key1"));
        assertEquals("value2", attributes.get("key2"));
    }
}
