
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class ImmutableStack_iteratorTest {
    private ImmutableStack<String> stack;

    @Before
    public void setUp() {
        stack = ImmutableStack.empty();
    }

    @Test
    public void testIteratorWithNonEmptyStack() {
        stack = stack.push("a").push("b").push("c");
        Iterator<String> iterator = stack.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("c", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("b", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("a", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testIteratorWithEmptyStack() {
        Iterator<String> iterator = stack.iterator();

        assertFalse(iterator.hasNext());
    }
}
