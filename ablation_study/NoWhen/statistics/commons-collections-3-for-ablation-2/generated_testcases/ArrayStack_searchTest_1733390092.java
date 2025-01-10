
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    public void testSearchElementFound() {
        stack.push("first");
        stack.push("second");
        stack.push("third");

        assertEquals(1, stack.search("third"));
        assertEquals(2, stack.search("second"));
        assertEquals(3, stack.search("first"));
    }

    @Test
    public void testSearchElementNotFound() {
        stack.push("first");
        stack.push("second");

        assertEquals(-1, stack.search("third"));
    }

    @Test
    public void testSearchNullElement() {
        stack.push("first");
        stack.push(null);
        stack.push("second");

        assertEquals(2, stack.search(null));
    }

    @Test
    public void testSearchEmptyStack() {
        assertEquals(-1, stack.search("anyElement"));
    }

    @Test
    public void testSearchAfterPop() {
        stack.push("first");
        stack.push("second");
        stack.push("third");

        stack.pop();
        assertEquals(2, stack.search("second"));
        assertEquals(-1, stack.search("third"));
    }

    @Test
    public void testSearchAfterPeek() {
        stack.push("first");
        stack.push("second");
        stack.push("third");

        stack.peek();
        assertEquals(1, stack.search("third"));
    }

    @Test
    public void testSearchAfterPeekWithIndex() {
        stack.push("first");
        stack.push("second");
        stack.push("third");

        stack.peek(1);
        assertEquals(2, stack.search("second"));
    }

    @Test
    public void testSearchWithEmptyStackException() {
        assertThrows(EmptyStackException.class, () -> stack.peek());
        assertThrows(EmptyStackException.class, () -> stack.pop());
    }
}
