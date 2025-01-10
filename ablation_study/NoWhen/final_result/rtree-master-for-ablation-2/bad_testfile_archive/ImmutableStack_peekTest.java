
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Iterator;
import org.junit.Test;

public class ImmutableStack_peekTest {

    @Test
    public void testPeekOnNonEmptyStack() {
        ImmutableStack<String> stack = ImmutableStack.create("first");
        stack = stack.push("second");
        assertEquals("second", stack.peek());
    }

    @Test
    public void testPeekOnEmptyStack() {
        ImmutableStack<String> stack = ImmutableStack.empty();
        assertEquals(null, stack.peek());
    }

    @Test
    public void testPeekAfterPush() {
        ImmutableStack<String> stack = ImmutableStack.create("first");
        stack = stack.push("second");
        assertEquals("second", stack.peek());
        stack = stack.push("third");
        assertEquals("third", stack.peek());
    }

    @Test
    public void testPeekAfterPop() {
        ImmutableStack<String> stack = ImmutableStack.create("first");
        stack = stack.push("second");
        stack = stack.push("third");
        stack = stack.pop();
        assertEquals("second", stack.peek());
    }

    @Test
    public void testPeekWithMultiplePushesAndPops() {
        ImmutableStack<String> stack = ImmutableStack.create("first");
        stack = stack.push("second");
        stack = stack.push("third");
        assertEquals("third", stack.peek());
        stack = stack.pop();
        assertEquals("second", stack.peek());
        stack = stack.pop();
        assertEquals("first", stack.peek());
    }
}
