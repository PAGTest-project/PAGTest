
package org.apache.commons.collections4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IteratorUtils_getTest {

    private List<String> list;
    private Iterator<String> iterator;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        iterator = list.iterator();
    }

    @Test
    public void testGetValidIndex() {
        assertEquals("B", IteratorUtils.get(iterator, 1));
    }

    @Test
    public void testGetInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            IteratorUtils.get(iterator, 3);
        });
    }

    @Test
    public void testGetFromEmptyIterator() {
        Iterator<String> emptyIterator = new ArrayList<String>().iterator();
        assertThrows(IndexOutOfBoundsException.class, () -> {
            IteratorUtils.get(emptyIterator, 0);
        });
    }

    @Test
    public void testGetNegativeIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            IteratorUtils.get(iterator, -1);
        });
    }
}
