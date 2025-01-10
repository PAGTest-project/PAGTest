
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.EmptyStackException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayStack_searchTest {

    private ArrayStack<String> stack;

    @BeforeEach
    public void setUp() {
        stack = new ArrayStack<>();
    }

    @Test
    public void testSearchFound() {
        stack.push("first");
        stack.push("second");
        stack.push("third");

        assertEquals(1, stack.search("third"));
        assertEquals(2, stack.search("second"));
        assertEquals(3, stack.search("first"));
    }

    @Test
    public void testSearchNotFound() {
        stack.push("first");
        stack.push("second");

        assertEquals(-1, stack.search("third"));
    }

    @Test
    public void testSearchNull() {
        stack.push("first");
        stack.push(null);
        stack.push("second");

        assertEquals(2, stack.search(null));
    }

    @Test
    public void testSearchEmptyStack() {
        assertTrue(stack.empty());
        assertEquals(-1, stack.search("anyObject"));
    }
}
