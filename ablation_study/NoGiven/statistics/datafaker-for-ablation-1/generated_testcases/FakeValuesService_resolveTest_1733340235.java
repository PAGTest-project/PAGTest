
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import net.datafaker.internal.helper.CopyOnWriteMap;
import java.util.WeakHashMap;

class FakeValuesService_resolveTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;
    private ProviderRegistration root;
    private Supplier<String> exceptionMessage;

    @BeforeEach
    void setUp() {
        fakeValuesService = new FakeValuesService();
        context = Mockito.mock(FakerContext.class);
        root = Mockito.mock(ProviderRegistration.class);
        exceptionMessage = Mockito.mock(Supplier.class);
    }

    @Test
    void testResolveWithRootNullAndExpressionFound() {
        String key = "testKey";
        String expectedExpression = "testExpression";
        when(context.getSingletonLocale()).thenReturn(FakeValuesService.DEFAULT_LOCALE);
        fakeValuesService.key2Expression.put(FakeValuesService.DEFAULT_LOCALE, new CopyOnWriteMap<>(() -> new WeakHashMap<>()));
        fakeValuesService.key2Expression.get(FakeValuesService.DEFAULT_LOCALE).put(key, expectedExpression);

        String result = fakeValuesService.resolve(key, null, null, exceptionMessage, context);

        assertEquals(expectedExpression, result);
    }

    @Test
    void testResolveWithRootNullAndExpressionNotFound() {
        String key = "testKey";
        String expectedExpression = "testExpression";
        when(context.getSingletonLocale()).thenReturn(FakeValuesService.DEFAULT_LOCALE);
        fakeValuesService.key2Expression.put(FakeValuesService.DEFAULT_LOCALE, new CopyOnWriteMap<>(() -> new WeakHashMap<>()));
        when(fakeValuesService.safeFetch(key, context, null)).thenReturn(expectedExpression);

        String result = fakeValuesService.resolve(key, null, null, exceptionMessage, context);

        assertEquals(expectedExpression, result);
    }

    @Test
    void testResolveWithRootNotNullAndExpressionNotFound() {
        String key = "testKey";
        String expectedExpression = "testExpression";
        when(fakeValuesService.safeFetch(key, context, null)).thenReturn(expectedExpression);

        String result = fakeValuesService.resolve(key, null, root, exceptionMessage, context);

        assertEquals(expectedExpression, result);
    }

    @Test
    void testResolveWithExpressionNullThrowsException() {
        String key = "testKey";
        String expectedErrorMessage = "Error message";
        when(exceptionMessage.get()).thenReturn(expectedErrorMessage);
        when(fakeValuesService.safeFetch(key, context, null)).thenReturn(null);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            fakeValuesService.resolve(key, null, root, exceptionMessage, context);
        });

        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}
