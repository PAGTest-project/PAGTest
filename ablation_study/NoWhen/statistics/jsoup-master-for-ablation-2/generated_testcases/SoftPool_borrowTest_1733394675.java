
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
    public void testBorrowFromEmptyPool() {
        char[] borrowed = softPool.borrow();
        assertNotNull(borrowed);
        assertEquals(BufSize, borrowed.length);
    }

    @Test
    public void testBorrowFromNonEmptyPool() {
        char[] buffer = new char[BufSize];
        softPool.release(buffer);

        char[] borrowed = softPool.borrow();
        assertNotNull(borrowed);
        assertEquals(BufSize, borrowed.length);
        assertSame(buffer, borrowed);
    }

    @Test
    public void testBorrowAfterRelease() {
        char[] buffer1 = new char[BufSize];
        char[] buffer2 = new char[BufSize];
        softPool.release(buffer1);
        softPool.release(buffer2);

        char[] borrowed1 = softPool.borrow();
        char[] borrowed2 = softPool.borrow();

        assertNotNull(borrowed1);
        assertNotNull(borrowed2);
        assertEquals(BufSize, borrowed1.length);
        assertEquals(BufSize, borrowed2.length);
        assertNotSame(borrowed1, borrowed2);
    }

    @Test
    public void testBorrowAfterMaxIdleReached() {
        for (int i = 0; i < SoftPool.MaxIdle + 1; i++) {
            softPool.release(new char[BufSize]);
        }

        char[] borrowed = softPool.borrow();
        assertNotNull(borrowed);
        assertEquals(BufSize, borrowed.length);
    }
}
