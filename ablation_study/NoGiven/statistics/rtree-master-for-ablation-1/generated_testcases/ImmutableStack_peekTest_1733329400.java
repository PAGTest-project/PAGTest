
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

public class ImmutableStack_peekTest {

    @Test
    public void testPeekOnNonEmptyStack() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1);
        assertEquals(Integer.valueOf(1), stack.peek());
    }

    @Test
    public void testPeekAfterPush() {
        ImmutableStack<Integer> stack = ImmutableStack.empty();
        stack = stack.push(1);
        assertEquals(Integer.valueOf(1), stack.peek());
    }

    @Test
    public void testPeekAfterMultiplePushes() {
        ImmutableStack<Integer> stack = ImmutableStack.empty();
        stack = stack.push(1);
        stack = stack.push(2);
        assertEquals(Integer.valueOf(2), stack.peek());
    }

    @Test
    public void testPeekOnEmptyStack() {
        ImmutableStack<Integer> stack = ImmutableStack.empty();
        try {
            stack.peek();
        } catch (RuntimeException e) {
            assertEquals("cannot peek on empty stack", e.getMessage());
        }
    }

    @Test
    public void testPeekAfterPop() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1);
        stack = stack.push(2);
        stack = stack.pop();
        assertEquals(Integer.valueOf(1), stack.peek());
    }

    @Test
    public void testPeekWithIterator() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1);
        Iterator<Integer> iterator = stack.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(1), iterator.next());
    }
}
