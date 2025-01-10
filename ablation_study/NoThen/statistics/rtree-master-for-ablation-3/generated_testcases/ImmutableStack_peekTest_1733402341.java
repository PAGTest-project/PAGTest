
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Test;

public class ImmutableStack_peekTest {

    @Test
    public void testPeekOnNonEmptyStack() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1);
        assertEquals(Integer.valueOf(1), stack.peek());
    }

    @Test(expected = RuntimeException.class)
    public void testPeekOnEmptyStackThrowsException() {
        ImmutableStack.empty().peek();
    }

    @Test
    public void testPeekAfterPush() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1);
        stack = stack.push(2);
        assertEquals(Integer.valueOf(2), stack.peek());
    }

    @Test
    public void testPeekAfterPop() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1);
        stack = stack.push(2);
        stack = stack.pop();
        assertEquals(Integer.valueOf(1), stack.peek());
    }

    @Test
    public void testPeekOnEmptyStackAfterMultiplePops() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1);
        stack = stack.push(2);
        stack = stack.pop();
        stack = stack.pop();
        assertEquals(Integer.valueOf(1), stack.peek());
    }
}
