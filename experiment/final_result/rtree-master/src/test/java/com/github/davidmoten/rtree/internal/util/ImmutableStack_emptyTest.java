
package com.github.davidmoten.rtree.internal.util;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class ImmutableStack_emptyTest {

    @Test
    public void testEmptyStackIsEmpty() {
        ImmutableStack<Object> emptyStack = ImmutableStack.empty();
        assertTrue(emptyStack.isEmpty());
    }
}
