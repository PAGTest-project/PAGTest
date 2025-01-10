
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ImmutableStack_peekTest {

    @Test
    public void testPeekOnNonEmptyStack() {
        ImmutableStack<String> stack = ImmutableStack.<String>empty().push("element");
        assertEquals("element", stack.peek());
    }

    @Test(expected = RuntimeException.class)
    public void testPeekOnEmptyStack() {
        ImmutableStack<String> stack = ImmutableStack.<String>empty();
        stack.peek(); // This should throw a RuntimeException
    }
}
