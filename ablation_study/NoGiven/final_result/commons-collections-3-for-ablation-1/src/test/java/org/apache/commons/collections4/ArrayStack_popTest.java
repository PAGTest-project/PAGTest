
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
    public void testPopWithElements() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals("Second Item", stack.pop(), "Top item should be 'Second Item'");
        assertEquals("First Item", stack.pop(), "Next item should be 'First Item'");
    }

    @Test
    public void testPopOnEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.pop(), "Pop on empty stack should throw EmptyStackException");
    }

    @Test
    public void testPopRemovesElement() {
        stack.push("Item");
        stack.pop();
        assertTrue(stack.empty(), "Stack should be empty after popping the only element");
    }

    @Test
    public void testPopAndPeekConsistency() {
        stack.push("Item");
        String peekedItem = stack.peek();
        String poppedItem = stack.pop();
        assertEquals(peekedItem, poppedItem, "Peeked and popped items should be the same");
    }

    @Test
    public void testPopAndSearchConsistency() {
        stack.push("Item");
        stack.pop();
        assertEquals(-1, stack.search("Item"), "Popped item should not be found in the stack");
    }
}
