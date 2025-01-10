
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.concurrent.Callable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BaseFaker_doWithTest {

    @Test
    void testDoWithSuccess() throws Exception {
        // Given
        BaseFaker baseFaker = new BaseFaker();
        FakerContext context = mock(FakerContext.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        baseFaker.context = context;
        baseFaker.fakeValuesService = fakeValuesService;

        Locale originalLocale = Locale.ENGLISH;
        Locale newLocale = Locale.FRENCH;
        when(context.getLocale()).thenReturn(originalLocale);

        Callable<String> callable = () -> "success";

        // When
        String result = baseFaker.doWith(callable, newLocale);

        // Then
        assertEquals("success", result);
        verify(context).setCurrentLocale(newLocale);
        verify(fakeValuesService).updateFakeValuesInterfaceMap(anyList());
        verify(context).setCurrentLocale(originalLocale);
    }

    @Test
    void testDoWithException() {
        // Given
        BaseFaker baseFaker = new BaseFaker();
        FakerContext context = mock(FakerContext.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        baseFaker.context = context;
        baseFaker.fakeValuesService = fakeValuesService;

        Locale originalLocale = Locale.ENGLISH;
        Locale newLocale = Locale.FRENCH;
        when(context.getLocale()).thenReturn(originalLocale);

        Callable<String> callable = () -> {
            throw new Exception("test exception");
        };

        // When
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            baseFaker.doWith(callable, newLocale);
        });

        // Then
        assertEquals("test exception", thrown.getCause().getMessage());
        verify(context).setCurrentLocale(newLocale);
        verify(fakeValuesService).updateFakeValuesInterfaceMap(anyList());
        verify(context).setCurrentLocale(originalLocale);
    }
}
