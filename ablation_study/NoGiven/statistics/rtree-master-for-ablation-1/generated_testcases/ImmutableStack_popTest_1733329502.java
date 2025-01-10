
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Test;

public class ImmutableStack_popTest {

    @Test
    public void testPopRemovesTopElement() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1).push(2).push(3);
        ImmutableStack<Integer> poppedStack = stack.pop();
        assertEquals(Integer.valueOf(2), poppedStack.peek());
    }

    @Test
    public void testPopOnEmptyStack() {
        ImmutableStack<Integer> emptyStack = ImmutableStack.empty();
        ImmutableStack<Integer> poppedStack = emptyStack.pop();
        assertTrue(poppedStack.isEmpty());
    }

    @Test
    public void testPopThenPush() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1).push(2).push(3);
        ImmutableStack<Integer> poppedStack = stack.pop();
        ImmutableStack<Integer> newStack = poppedStack.push(4);
        assertEquals(Integer.valueOf(4), newStack.peek());
    }

    @Test
    public void testPopThenCheckIsEmpty() {
        ImmutableStack<Integer> stack = ImmutableStack.create(1).push(2).push(3);
        ImmutableStack<Integer> poppedStack = stack.pop();
        assertFalse(poppedStack.isEmpty());
    }
}
