
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
    public void testPeekWithElements() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals("Second Item", stack.peek(), "Top item should be 'Second Item'");
    }

    @Test
    public void testPeekEmptyStack() {
        assertThrows(EmptyStackException.class, () -> {
            stack.peek();
        }, "Peeking an empty stack should throw EmptyStackException");
    }
}
