
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
        assertEquals(-1, stack.search("Any Item"), "Empty stack should return -1");
    }

    @Test
    public void testSearchNullItem() {
        stack.push(null);
        stack.push("First Item");
        assertEquals(2, stack.search(null), "Null item should be found");
    }

    @Test
    public void testSearchNullInEmptyStack() {
        assertEquals(-1, stack.search(null), "Null in empty stack should return -1");
    }
}
