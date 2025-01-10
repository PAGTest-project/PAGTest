
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

public class SoftPool_releaseTest {
    private static final int BufSize = 1024;
    private SoftPool<char[]> softPool;

    @BeforeEach
    public void setUp() {
        softPool = new SoftPool<>(() -> new char[BufSize]);
    }

    @Test
    public void testReleaseWhenStackIsNotFull() {
        char[] value = new char[BufSize];
        softPool.release(value);
        Stack<char[]> stack = softPool.getStack();
        assertEquals(1, stack.size());
        assertSame(value, stack.peek());
    }

    @Test
    public void testReleaseWhenStackIsFull() {
        Stack<char[]> stack = softPool.getStack();
        for (int i = 0; i < SoftPool.MaxIdle; i++) {
            stack.push(new char[BufSize]);
        }
        char[] value = new char[BufSize];
        softPool.release(value);
        assertEquals(SoftPool.MaxIdle, stack.size());
        assertNotSame(value, stack.peek());
    }

    @Test
    public void testReleaseAfterBorrow() {
        char[] borrowedValue = softPool.borrow();
        assertNotNull(borrowedValue);
        softPool.release(borrowedValue);
        Stack<char[]> stack = softPool.getStack();
        assertEquals(1, stack.size());
        assertSame(borrowedValue, stack.peek());
    }
}
