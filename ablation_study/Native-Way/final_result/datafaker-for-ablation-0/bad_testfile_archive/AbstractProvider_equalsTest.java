
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AbstractProvider_equalsTest {
    private AbstractProvider<?> provider1;
    private AbstractProvider<?> provider2;

    @BeforeEach
    public void setUp() {
        ProviderRegistration faker = new ProviderRegistration() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation of the abstract method
            }
        };
        provider1 = new AbstractProvider<>(faker);
        provider2 = new AbstractProvider<>(faker);
    }

    @Test
    public void testEqualsSameObject() {
        assertTrue(provider1.equals(provider1));
    }

    @Test
    public void testEqualsDifferentClass() {
        assertFalse(provider1.equals(new Object()));
    }

    @Test
    public void testEqualsDifferentFaker() {
        provider2 = new AbstractProvider<>(new ProviderRegistration() {
            @Override
            public void addUrl(java.util.Locale locale, java.net.URL url) {
                // Implementation of the abstract method
            }
        });
        assertFalse(provider1.equals(provider2));
    }

    @Test
    public void testEqualsSameFaker() {
        provider2 = new AbstractProvider<>(provider1.getFaker());
        assertTrue(provider1.equals(provider2));
    }

    @Test
    public void testHashCodeConsistency() {
        assertEquals(provider1.hashCode(), provider1.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        provider2 = new AbstractProvider<>(provider1.getFaker());
        assertEquals(provider1.hashCode(), provider2.hashCode());
    }

    @Test
    public void testToString() {
        assertNotNull(provider1.toString());
        assertTrue(provider1.toString().contains(provider1.getClass().getSimpleName()));
        assertTrue(provider1.toString().contains(provider1.getFaker().toString()));
    }
}
