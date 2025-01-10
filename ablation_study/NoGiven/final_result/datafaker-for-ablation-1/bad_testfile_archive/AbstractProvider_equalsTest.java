
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractProvider_equalsTest {
    private AbstractProvider<?> provider1;
    private AbstractProvider<?> provider2;
    private AbstractProvider<?> provider3;

    @BeforeEach
    public void setUp() {
        provider1 = new AbstractProvider<>(new BaseFaker());
        provider2 = new AbstractProvider<>(new BaseFaker());
        provider3 = new AbstractProvider<>(new BaseFaker());
    }

    @Test
    public void testEqualsSameInstance() {
        assertTrue(provider1.equals(provider1));
    }

    @Test
    public void testEqualsDifferentInstanceSameFaker() {
        assertTrue(provider1.equals(provider2));
    }

    @Test
    public void testEqualsDifferentInstanceDifferentFaker() {
        assertFalse(provider1.equals(provider3));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(provider1.equals(new Object()));
    }

    @Test
    public void testHashCodeConsistency() {
        assertEquals(provider1.hashCode(), provider2.hashCode());
        assertNotEquals(provider1.hashCode(), provider3.hashCode());
    }
}
