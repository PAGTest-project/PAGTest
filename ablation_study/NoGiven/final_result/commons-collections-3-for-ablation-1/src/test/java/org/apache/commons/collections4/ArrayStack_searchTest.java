
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
    public void testSearchTopElement() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals(1, stack.search("Second Item"), "Top item is 'Second Item'");
    }

    @Test
    public void testSearchMiddleElement() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.push("Third Item");
        assertEquals(2, stack.search("Second Item"), "Middle item is 'Second Item'");
    }

    @Test
    public void testSearchBottomElement() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.push("Third Item");
        assertEquals(3, stack.search("First Item"), "Bottom item is 'First Item'");
    }

    @Test
    public void testSearchMissingElement() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals(-1, stack.search("Missing Item"), "Cannot find 'Missing Item'");
    }

    @Test
    public void testSearchEmptyStack() {
        assertEquals(-1, stack.search("Any Item"), "Stack is empty, cannot find any item");
    }

    @Test
    public void testSearchAfterPop() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.pop();
        assertEquals(1, stack.search("First Item"), "Top item after pop is 'First Item'");
    }

    @Test
    public void testSearchNullElement() {
        stack.push(null);
        stack.push("First Item");
        assertEquals(2, stack.search(null), "Null element is found");
    }

    @Test
    public void testSearchNullInEmptyStack() {
        assertEquals(-1, stack.search(null), "Null element not found in empty stack");
    }

    @Test
    public void testSearchNullInNonEmptyStack() {
        stack.push("First Item");
        assertEquals(-1, stack.search(null), "Null element not found in non-empty stack");
    }
}
