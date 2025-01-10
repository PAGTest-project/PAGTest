
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.function.Function;
import java.util.Map;
import java.util.IdentityHashMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class BaseFaker_getProviderTest {

    private BaseFaker baseFaker;
    private Map<Class<?>, AbstractProvider<?>> providersCache;

    @BeforeEach
    void setUp() {
        baseFaker = new BaseFaker() {
            @Override
            public <PR extends ProviderRegistration, AP extends AbstractProvider<PR>> AP getProvider(
                    Class<AP> clazz, Function<PR, AP> valueSupplier) {
                return (AP) providersCache.computeIfAbsent(clazz, (klass) -> valueSupplier.apply(getFaker()));
            }
        };
        providersCache = spy(new IdentityHashMap<>());
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
