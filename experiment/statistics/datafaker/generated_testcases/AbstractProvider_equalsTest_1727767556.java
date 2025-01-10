
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AbstractProvider_equalsTest {
    private AbstractProvider<?> provider1;
    private AbstractProvider<?> provider2;
    private AbstractProvider<?> provider3;

    @BeforeEach
    void setUp() {
        provider1 = new AbstractProvider<>(new BaseFaker());
        provider2 = new AbstractProvider<>(new BaseFaker());
        provider3 = new AbstractProvider<>(new BaseFaker());
    }

    @Test
    void testEqualsSameInstance() {
        assertTrue(provider1.equals(provider1));
    }

    @Test
    void testEqualsDifferentInstancesSameFaker() {
        assertTrue(provider1.equals(provider2));
    }

    @Test
    void testEqualsDifferentFaker() {
        provider3 = new AbstractProvider<>(new BaseFaker());
        assertFalse(provider1.equals(provider3));
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(provider1.equals(new Object()));
    }
}
