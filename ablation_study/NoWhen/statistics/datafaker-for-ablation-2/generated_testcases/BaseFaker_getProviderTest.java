
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.util.function.Function;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class BaseFaker_getProviderTest {

    @Test
    void testGetProvider() {
        // Given
        BaseFaker baseFaker = new BaseFaker();
        Class<TestProvider> clazz = TestProvider.class;
        Function<ProviderRegistration, TestProvider> valueSupplier = mock(Function.class);
        TestProvider expectedProvider = new TestProvider(baseFaker);
        when(valueSupplier.apply(any())).thenReturn(expectedProvider);

        // When
        TestProvider actualProvider = baseFaker.getProvider(clazz, valueSupplier);

        // Then
        assertNotNull(actualProvider);
        assertEquals(expectedProvider, actualProvider);
    }

    static class TestProvider extends AbstractProvider<ProviderRegistration> {
        protected TestProvider(ProviderRegistration faker) {
            super(faker);
        }
    }
}
