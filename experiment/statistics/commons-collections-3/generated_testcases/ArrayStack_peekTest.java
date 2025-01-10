
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
    public void testPeekOnNonEmptyStack() {
        stack.push("item1");
        stack.push("item2");
        assertEquals("item2", stack.peek());
    }

    @Test
    public void testPeekOnEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.peek());
    }
}
