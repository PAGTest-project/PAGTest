
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;

public class Attributes_iteratorTest {
    private Attributes attributes;

    @BeforeEach
    public void setUp() {
        attributes = new Attributes();
    }

    @Test
    public void testIteratorHasNext() {
        attributes.put("key1", "value1");
        Iterator<Attribute> iterator = attributes.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        attributes.put("key1", "value1");
        Iterator<Attribute> iterator = attributes.iterator();
        Attribute attr = iterator.next();
        assertEquals("key1", attr.getKey());
        assertEquals("value1", attr.getValue());
    }

    @Test
    public void testIteratorNextNoSuchElementException() {
        Iterator<Attribute> iterator = attributes.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorRemove() {
        attributes.put("key1", "value1");
        Iterator<Attribute> iterator = attributes.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(0, attributes.size());
    }

    @Test
    public void testIteratorConcurrentModificationException() {
        attributes.put("key1", "value1");
        Iterator<Attribute> iterator = attributes.iterator();
        attributes.put("key2", "value2");
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }

    @Test
    public void testIteratorSkipInternalKeys() {
        attributes.put("/internalKey", "value1");
        attributes.put("key1", "value1");
        Iterator<Attribute> iterator = attributes.iterator();
        Attribute attr = iterator.next();
        assertEquals("key1", attr.getKey());
    }
}
