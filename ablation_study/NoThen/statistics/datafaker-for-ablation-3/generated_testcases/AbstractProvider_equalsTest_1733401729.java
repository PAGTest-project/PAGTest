
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractProvider_equalsTest {
    private AbstractProvider<?> provider1;
    private AbstractProvider<?> provider2;

    @BeforeEach
    public void setUp() {
        provider1 = new AbstractProvider<>(new BaseFaker());
        provider2 = new AbstractProvider<>(new BaseFaker());
    }

    @Test
    public void testEqualsSameInstance() {
        assertTrue(provider1.equals(provider1));
    }

    @Test
    public void testEqualsDifferentInstancesSameFaker() {
        assertTrue(provider1.equals(provider2));
    }

    @Test
    public void testEqualsDifferentFakers() {
        provider2 = new AbstractProvider<>(new BaseFaker());
        assertFalse(provider1.equals(provider2));
    }

    @Test
    public void testEqualsDifferentClass() {
        Object differentObject = new Object();
        assertFalse(provider1.equals(differentObject));
    }

    @Test
    public void testEqualsNull() {
        assertFalse(provider1.equals(null));
    }
}
