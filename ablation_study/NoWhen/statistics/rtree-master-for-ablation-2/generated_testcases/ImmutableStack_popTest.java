
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class ImmutableStack_popTest {

    @Test
    public void testPopOnNonEmptyStack() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1).push(2);
        ImmutableStack<Integer> poppedStack = stack.pop();
        assertFalse(poppedStack.isEmpty());
        assertEquals(Integer.valueOf(1), poppedStack.peek());
    }

    @Test
    public void testPopOnSingleElementStack() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1);
        ImmutableStack<Integer> poppedStack = stack.pop();
        assertTrue(poppedStack.isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testPopOnEmptyStack() {
        ImmutableStack<Integer> stack = ImmutableStack.empty();
        stack.pop();
    }
}
