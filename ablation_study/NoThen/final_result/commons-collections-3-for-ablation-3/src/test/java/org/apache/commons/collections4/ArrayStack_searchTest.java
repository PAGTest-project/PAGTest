
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
        stack = new ArrayStack<>(3);
    }

    @Test
    public void testSearchTopItem() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals(1, stack.search("Second Item"), "Top item is 'Second Item'");
    }

    @Test
    public void testSearchMiddleItem() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.push("Third Item");
        assertEquals(2, stack.search("Second Item"), "Middle item is 'Second Item'");
    }

    @Test
    public void testSearchBottomItem() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.push("Third Item");
        assertEquals(3, stack.search("First Item"), "Bottom item is 'First Item'");
    }

    @Test
    public void testSearchMissingItem() {
        stack.push("First Item");
        stack.push("Second Item");
        assertEquals(-1, stack.search("Missing Item"), "Cannot find 'Missing Item'");
    }

    @Test
    public void testSearchEmptyStack() {
        assertEquals(-1, stack.search("Any Item"), "Stack is empty, cannot find any item");
    }

    @Test
    public void testSearchNullItem() {
        stack.push("First Item");
        stack.push(null);
        assertEquals(1, stack.search(null), "Top item is null");
    }

    @Test
    public void testSearchNullInEmptyStack() {
        assertEquals(-1, stack.search(null), "Stack is empty, cannot find null");
    }

    @Test
    public void testSearchAfterPop() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.pop();
        assertEquals(1, stack.search("First Item"), "Top item is 'First Item' after pop");
    }

    @Test
    public void testSearchAfterMultiplePops() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.push("Third Item");
        stack.pop();
        stack.pop();
        assertEquals(1, stack.search("First Item"), "Top item is 'First Item' after multiple pops");
    }

    @Test
    public void testSearchAfterPushAndPop() {
        stack.push("First Item");
        stack.push("Second Item");
        stack.pop();
        stack.push("Third Item");
        assertEquals(2, stack.search("First Item"), "Middle item is 'First Item' after push and pop");
    }
}
