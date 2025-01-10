
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayStack_peekTest {

    private ArrayStack<String> stack;

    @BeforeEach
    public void setUp() {
        stack = new ArrayStack<>();
    }

    @Test
    public void testPeekWithEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }

    @Test
    public void testPeekWithNonEmptyStack() {
        stack.push("Item1");
        assertEquals("Item1", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    public void testPeekAfterPushAndPop() {
        stack.push("Item1");
        stack.push("Item2");
        stack.pop();
        assertEquals("Item1", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    public void testPeekAfterMultiplePushes() {
        stack.push("Item1");
        stack.push("Item2");
        stack.push("Item3");
        assertEquals("Item3", stack.peek());
        assertEquals(3, stack.size());
    }
}
