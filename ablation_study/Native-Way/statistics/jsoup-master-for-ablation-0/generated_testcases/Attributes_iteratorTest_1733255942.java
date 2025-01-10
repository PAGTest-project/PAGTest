
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
        attributes.add("Key1", "Val1");
        attributes.add("Key2", "Val2");
        attributes.add("Key3", null);
    }

    @Test
    public void testIteratorHasNext() {
        Iterator<Attribute> iterator = attributes.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorNext() {
        Iterator<Attribute> iterator = attributes.iterator();
        assertEquals("Key1", iterator.next().getKey());
        assertEquals("Key2", iterator.next().getKey());
        assertEquals("Key3", iterator.next().getKey());
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    public void testIteratorRemove() {
        Iterator<Attribute> iterator = attributes.iterator();
        iterator.next();
        iterator.remove();
        assertEquals(2, attributes.size());
        assertEquals("Key2", attributes.iterator().next().getKey());
    }

    @Test
    public void testIteratorConcurrentModification() {
        Iterator<Attribute> iterator = attributes.iterator();
        attributes.add("Key4", "Val4");
        assertThrows(ConcurrentModificationException.class, iterator::hasNext);
    }

    @Test
    public void testIteratorNoSuchElementException() {
        Iterator<Attribute> iterator = attributes.iterator();
        iterator.next();
        iterator.next();
        iterator.next();
        assertThrows(NoSuchElementException.class, iterator::next);
    }
}
