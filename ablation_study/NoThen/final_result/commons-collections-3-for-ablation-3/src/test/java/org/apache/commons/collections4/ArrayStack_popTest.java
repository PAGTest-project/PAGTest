
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

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
    public void testPopWithItems() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals("Second Item", stack.pop(), "Top item should be 'Second Item'");
        assertEquals("First Item", stack.pop(), "Next item should be 'First Item'");
    }

    @Test
    public void testPopOnEmptyStack() {
        assertThrows(EmptyStackException.class, () -> {
            stack.pop();
        }, "Pop on empty stack should throw EmptyStackException");
    }

    @Test
    public void testPopAfterPeek() {
        stack.push("Item");
        assertEquals("Item", stack.peek(), "Peek should return 'Item'");
        assertEquals("Item", stack.pop(), "Pop should return 'Item'");
        assertTrue(stack.empty(), "Stack should be empty after pop");
    }

    @Test
    public void testPopAndSearch() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals(1, stack.search("Second Item"), "Top item is 'Second Item'");
        assertEquals("Second Item", stack.pop(), "Pop should return 'Second Item'");
        assertEquals(1, stack.search("First Item"), "Top item is 'First Item'");
    }

    @Test
    public void testPopAndEmpty() {
        stack.push("Item");
        assertFalse(stack.empty(), "Stack should not be empty after push");
        stack.pop();
        assertTrue(stack.empty(), "Stack should be empty after pop");
    }
}
