
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ThreadLocals_letTest {

    @Test
    public void testLet() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("originalValue");
        Runnable runnable = mock(Runnable.class);

        ThreadLocals.let(threadLocal, "newValue", runnable);

        verify(runnable).run();
        assertEquals("originalValue", threadLocal.get());
    }
}
