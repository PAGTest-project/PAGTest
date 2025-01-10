
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayStack_popTest {

    private ArrayStack<String> stack;

    @BeforeEach
    public void setUp() {
        stack = new ArrayStack<>();
    }

    @Test
    public void testPopWithNonEmptyStack() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals("Second Item", stack.pop(), "Top item should be 'Second Item'");
        assertEquals("First Item", stack.pop(), "Next item should be 'First Item'");
    }

    @Test
    public void testPopWithEmptyStack() {
        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        }, "Popping from an empty stack should throw EmptyStackException");
    }

    @Test
    public void testPopWithSingleItemStack() {
        stack.push("Single Item");
        assertEquals("Single Item", stack.pop(), "Only item should be 'Single Item'");
        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        }, "Stack should be empty after popping the only item");
    }
}
