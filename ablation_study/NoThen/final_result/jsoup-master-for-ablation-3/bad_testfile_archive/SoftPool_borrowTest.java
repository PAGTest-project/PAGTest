
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

public class SoftPool_borrowTest {
    private static final int BufSize = 1024;
    private SoftPool<char[]> softPool;

    @BeforeEach
    public void setUp() {
        softPool = new SoftPool<>(() -> new char[BufSize]);
    }

    @Test
    public void testBorrowWithEmptyStack() {
        char[] borrowed = softPool.borrow();
        assertNotNull(borrowed);
        assertEquals(BufSize, borrowed.length);
    }

    @Test
    public void testBorrowWithNonEmptyStack() {
        char[] buffer = new char[BufSize];
        softPool.release(buffer);
        char[] borrowed = softPool.borrow();
        assertSame(buffer, borrowed);
    }

    @Test
    public void testBorrowAndRelease() {
        char[] buffer1 = softPool.borrow();
        char[] buffer2 = softPool.borrow();
        assertNotSame(buffer1, buffer2);

        softPool.release(buffer1);
        char[] borrowedAgain = softPool.borrow();
        assertSame(buffer1, borrowedAgain);
    }

    @Test
    public void testBorrowWithMultipleReleases() {
        char[] buffer1 = new char[BufSize];
        char[] buffer2 = new char[BufSize];
        softPool.release(buffer1);
        softPool.release(buffer2);

        char[] borrowed1 = softPool.borrow();
        char[] borrowed2 = softPool.borrow();
        assertNotSame(borrowed1, borrowed2);
    }

    @Test
    public void testBorrowWithMaxIdle() {
        Stack<char[]> stack = softPool.getStack();
        for (int i = 0; i < SoftPool.MaxIdle; i++) {
            softPool.release(new char[BufSize]);
        }
        assertEquals(SoftPool.MaxIdle, stack.size());

        char[] buffer = new char[BufSize];
        softPool.release(buffer);
        assertEquals(SoftPool.MaxIdle, stack.size());

        char[] borrowed = softPool.borrow();
        assertNotNull(borrowed);
    }
}
