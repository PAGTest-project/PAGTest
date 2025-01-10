
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BaseFaker_getProviderTest {

    private BaseFaker baseFaker;
    private Map<Class<?>, AbstractProvider<?>> providersCache;

    @BeforeEach
    void setUp() {
        baseFaker = new BaseFaker();
        providersCache = spy(new IdentityHashMap<>());
        baseFaker.providersCache = providersCache;
    }

    @Test
    void testGetProvider() {
        // Given
        Class<AbstractProvider<ProviderRegistration>> clazz = (Class<AbstractProvider<ProviderRegistration>>) (Class<?>) AbstractProvider.class;
        Function<ProviderRegistration, AbstractProvider<ProviderRegistration>> valueSupplier = mock(Function.class);
        AbstractProvider<ProviderRegistration> expectedProvider = mock(AbstractProvider.class);
        when(valueSupplier.apply(any())).thenReturn(expectedProvider);

        // When
        AbstractProvider<ProviderRegistration> result = baseFaker.getProvider(clazz, valueSupplier);

        // Then
        assertEquals(expectedProvider, result);
        verify(providersCache).computeIfAbsent(eq(clazz), any());
    }
}
