
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
    public void testBorrowWhenStackIsEmpty() {
        char[] result = softPool.borrow();
        assertNotNull(result);
        assertEquals(BufSize, result.length);
    }

    @Test
    public void testBorrowWhenStackIsNotEmpty() {
        char[] buffer = new char[BufSize];
        softPool.release(buffer);
        char[] result = softPool.borrow();
        assertNotNull(result);
        assertEquals(BufSize, result.length);
        assertSame(buffer, result);
    }

    @Test
    public void testBorrowAfterReleasingMultipleObjects() {
        char[] buffer1 = new char[BufSize];
        char[] buffer2 = new char[BufSize];
        softPool.release(buffer1);
        softPool.release(buffer2);
        char[] result1 = softPool.borrow();
        char[] result2 = softPool.borrow();
        assertNotNull(result1);
        assertNotNull(result2);
        assertEquals(BufSize, result1.length);
        assertEquals(BufSize, result2.length);
        assertSame(buffer2, result1); // The last released object should be the first borrowed
        assertSame(buffer1, result2);
    }

    @Test
    public void testBorrowWithMaxIdleLimit() {
        for (int i = 0; i < SoftPool.MaxIdle + 1; i++) {
            softPool.release(new char[BufSize]);
        }
        char[] result = softPool.borrow();
        assertNotNull(result);
        assertEquals(BufSize, result.length);
    }
}
