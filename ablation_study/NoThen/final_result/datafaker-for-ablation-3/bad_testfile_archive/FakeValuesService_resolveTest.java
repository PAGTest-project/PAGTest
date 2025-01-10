
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.function.Supplier;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_resolveTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;
    private ProviderRegistration root;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, new RandomService());
        root = new ProviderRegistration() {
            @Override
            public FakerContext getContext() {
                return context;
            }

            @Override
            public Object getProvider(String providerClassName) {
                return null;
            }

            @Override
            public Object getProvider(Class<?> providerClass, java.util.function.Function<ProviderRegistration, Object> providerFunction) {
                return null;
            }
        };
    }

    @Test
    public void testResolveWithValidKey() {
        String key = "validKey";
        Object current = new Object();
        Supplier<String> exceptionMessage = () -> "Exception message";

        String result = fakeValuesService.resolve(key, current, root, exceptionMessage, context);

        assertNotNull(result);
        assertNotEquals("Exception message", result);
    }

    @Test
    public void testResolveWithInvalidKey() {
        String key = "invalidKey";
        Object current = new Object();
        Supplier<String> exceptionMessage = () -> "Exception message";

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fakeValuesService.resolve(key, current, root, exceptionMessage, context);
        });

        assertEquals("Exception message", exception.getMessage());
    }

    @Test
    public void testResolveWithNullRoot() {
        String key = "validKey";
        Object current = new Object();
        Supplier<String> exceptionMessage = () -> "Exception message";

        String result = fakeValuesService.resolve(key, current, null, exceptionMessage, context);

        assertNotNull(result);
        assertNotEquals("Exception message", result);
    }

    @Test
    public void testResolveWithNullCurrent() {
        String key = "validKey";
        Supplier<String> exceptionMessage = () -> "Exception message";

        String result = fakeValuesService.resolve(key, null, root, exceptionMessage, context);

        assertNotNull(result);
        assertNotEquals("Exception message", result);
    }

    @Test
    public void testResolveWithNullExceptionMessage() {
        String key = "validKey";
        Object current = new Object();

        String result = fakeValuesService.resolve(key, current, root, null, context);

        assertNotNull(result);
    }
}
