
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

public class SoftPool_borrowTest {
    private SoftPool<char[]> softPool;
    private static final int BufSize = 1024;

    @BeforeEach
    public void setUp() {
        softPool = new SoftPool<>(() -> new char[BufSize]);
    }

    @Test
    public void testBorrowFromEmptyPool() {
        char[] buffer = softPool.borrow();
        assertNotNull(buffer);
        assertEquals(BufSize, buffer.length);
    }

    @Test
    public void testBorrowFromNonEmptyPool() {
        char[] buffer1 = new char[BufSize];
        softPool.release(buffer1);

        char[] buffer2 = softPool.borrow();
        assertNotNull(buffer2);
        assertEquals(BufSize, buffer2.length);
        assertSame(buffer1, buffer2);
    }
}
