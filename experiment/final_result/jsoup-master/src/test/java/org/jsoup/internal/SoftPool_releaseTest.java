
package org.jsoup.internal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Stack;
import static org.junit.jupiter.api.Assertions.*;

public class SoftPool_releaseTest {
    private SoftPool<char[]> softPool;
    private static final int BufSize = 1024;
    private static final int MaxIdle = 12;

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
        for (int i = 0; i < MaxIdle; i++) {
            stack.push(new char[BufSize]);
        }

        char[] value = new char[BufSize];
        softPool.release(value);

        assertEquals(MaxIdle, stack.size());
        assertNotSame(value, stack.peek());
    }
}
