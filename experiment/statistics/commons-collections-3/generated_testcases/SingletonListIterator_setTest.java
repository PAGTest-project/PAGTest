
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SingletonListIterator_setTest {

    private SingletonListIterator<String> iterator;
    private static final String testValue = "testObject";

    @BeforeEach
    public void setUp() {
        iterator = new SingletonListIterator<>(testValue);
    }

    @Test
    public void testSetSuccess() {
        iterator.next();
        iterator.set("newTestObject");
        assertEquals("newTestObject", iterator.previous());
    }

    @Test
    public void testSetWithoutNextCall() {
        assertThrows(IllegalStateException.class, () -> iterator.set("newTestObject"));
    }

    @Test
    public void testSetAfterRemove() {
        iterator.next();
        iterator.remove();
        assertThrows(IllegalStateException.class, () -> iterator.set("newTestObject"));
    }
}
