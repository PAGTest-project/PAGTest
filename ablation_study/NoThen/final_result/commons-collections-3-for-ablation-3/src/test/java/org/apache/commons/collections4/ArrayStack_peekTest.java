
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
    public void testPeekWithNonEmptyStack() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals("Second Item", stack.peek(), "Top item should be 'Second Item'");
    }

    @Test
    public void testPeekWithEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.peek(), "Peeking an empty stack should throw EmptyStackException");
    }

    @Test
    public void testPeekAfterPop() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.pop();
        assertEquals("First Item", stack.peek(), "Top item should be 'First Item' after pop");
    }

    @Test
    public void testPeekWithSingleItemStack() {
        stack.push("Single Item");
        assertEquals("Single Item", stack.peek(), "Top item should be 'Single Item'");
    }

    @Test
    public void testPeekWithMultiplePushesAndPops() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.push("Third Item");
        stack.pop();
        stack.pop();
        assertEquals("First Item", stack.peek(), "Top item should be 'First Item' after multiple pushes and pops");
    }
}
