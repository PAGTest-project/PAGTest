
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
        stack.push("item1");
        stack.push("item2");
        assertEquals("item2", stack.pop());
        assertEquals("item1", stack.pop());
    }

    @Test
    public void testPopWithEmptyStack() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }
}
