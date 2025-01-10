
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.IdentityHashMap;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BaseFaker_getProviderTest {

    private BaseFaker baseFaker;
    private Map<Class<?>, AbstractProvider<?>> providersCache;

    @BeforeEach
    void setUp() {
        baseFaker = new BaseFaker();
        providersCache = new IdentityHashMap<>();
        baseFaker.providersCache = providersCache;
    }

    @Test
    void testGetProvider() {
        // Given
        Class<TestProvider> clazz = TestProvider.class;
        Function<ProviderRegistration, TestProvider> valueSupplier = mock(Function.class);
        ProviderRegistration faker = baseFaker.getFaker();
        TestProvider expectedProvider = new TestProvider(faker);
        when(valueSupplier.apply(faker)).thenReturn(expectedProvider);

        // When
        TestProvider actualProvider = baseFaker.getProvider(clazz, valueSupplier);

        // Then
        assertEquals(expectedProvider, actualProvider);
        assertEquals(expectedProvider, providersCache.get(clazz));
    }

    static class TestProvider extends AbstractProvider<ProviderRegistration> {
        protected TestProvider(ProviderRegistration faker) {
            super(faker);
        }
    }
}
