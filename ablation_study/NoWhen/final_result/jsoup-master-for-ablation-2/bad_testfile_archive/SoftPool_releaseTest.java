
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

public class SoftPool_releaseTest {
    private SoftPool<char[]> softPool;
    private static final int BufSize = 1024;

    @BeforeEach
    public void setUp() {
        softPool = new SoftPool<>(() -> new char[BufSize]);
    }

    @Test
    public void testReleaseWhenStackIsNotFull() {
        char[] buffer = new char[BufSize];
        softPool.release(buffer);

        Stack<char[]> stack = softPool.getStack();
        assertEquals(1, stack.size());
        assertSame(buffer, stack.peek());
    }

    @Test
    public void testReleaseWhenStackIsFull() {
        Stack<char[]> stack = softPool.getStack();
        for (int i = 0; i < SoftPool.MaxIdle; i++) {
            stack.push(new char[BufSize]);
        }

        char[] buffer = new char[BufSize];
        softPool.release(buffer);

        assertEquals(SoftPool.MaxIdle, stack.size());
        assertNotSame(buffer, stack.peek());
    }

    @Test
    public void testReleaseAndBorrow() {
        char[] buffer = new char[BufSize];
        softPool.release(buffer);

        char[] borrowedBuffer = softPool.borrow();
        assertSame(buffer, borrowedBuffer);

        Stack<char[]> stack = softPool.getStack();
        assertTrue(stack.isEmpty());
    }
}
