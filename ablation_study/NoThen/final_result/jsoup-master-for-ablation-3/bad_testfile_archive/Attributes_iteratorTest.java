
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
        attributes.put("key2", "value2");
        Iterator<Attribute> iterator = attributes.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        Iterator<Attribute> iterator = attributes.iterator();
        Attribute attr1 = iterator.next();
        assertEquals("key1", attr1.getKey());
        assertEquals("value1", attr1.getValue());
        Attribute attr2 = iterator.next();
        assertEquals("key2", attr2.getKey());
        assertEquals("value2", attr2.getValue());
    }

    @Test
    public void testIteratorNextThrowsNoSuchElementException() {
        Iterator<Attribute> iterator = attributes.iterator();
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorRemove() {
        attributes.put("key1", "value1");
        attributes.put("key2", "value2");
        Iterator<Attribute> iterator = attributes.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(1, attributes.size());
        assertEquals("key2", attributes.get(0).getKey());
    }

    @Test
    public void testIteratorConcurrentModification() {
        attributes.put("key1", "value1");
        Iterator<Attribute> iterator = attributes.iterator();
        attributes.put("key2", "value2");
        assertThrows(ConcurrentModificationException.class, iterator::next);
    }
}
