
package net.datafaker.providers.base;

import org.junit.jupiter.api.Test;

import java.util.Locale;
import java.util.concurrent.Callable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BaseFaker_doWithTest {

    @Test
    public void testDoWith_SuccessfulExecution() throws Exception {
        // Given
        BaseFaker baseFaker = new BaseFaker();
        FakerContext context = mock(FakerContext.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        baseFaker.context = context;
        baseFaker.fakeValuesService = fakeValuesService;

        Locale newLocale = Locale.FRENCH;
        Locale currentLocale = Locale.ENGLISH;
        when(context.getLocale()).thenReturn(currentLocale);

        Callable<String> callable = () -> "success";

        // When
        String result = baseFaker.doWith(callable, newLocale);

        // Then
        assertEquals("success", result);
        verify(context).setCurrentLocale(newLocale);
        verify(fakeValuesService).updateFakeValuesInterfaceMap(anyList());
        verify(context).setCurrentLocale(currentLocale);
        verify(fakeValuesService, times(2)).updateFakeValuesInterfaceMap(anyList());
    }

    @Test
    public void testDoWith_RuntimeException() {
        // Given
        BaseFaker baseFaker = new BaseFaker();
        FakerContext context = mock(FakerContext.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        baseFaker.context = context;
        baseFaker.fakeValuesService = fakeValuesService;

        Locale newLocale = Locale.FRENCH;
        Locale currentLocale = Locale.ENGLISH;
        when(context.getLocale()).thenReturn(currentLocale);

        Callable<String> callable = () -> {
            throw new RuntimeException("test exception");
        };

        // When
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            baseFaker.doWith(callable, newLocale);
        });

        // Then
        assertEquals("test exception", exception.getMessage());
        verify(context).setCurrentLocale(newLocale);
        verify(fakeValuesService).updateFakeValuesInterfaceMap(anyList());
        verify(context).setCurrentLocale(currentLocale);
        verify(fakeValuesService, times(2)).updateFakeValuesInterfaceMap(anyList());
    }

    @Test
    public void testDoWith_CheckedException() {
        // Given
        BaseFaker baseFaker = new BaseFaker();
        FakerContext context = mock(FakerContext.class);
        FakeValuesService fakeValuesService = mock(FakeValuesService.class);
        baseFaker.context = context;
        baseFaker.fakeValuesService = fakeValuesService;

        Locale newLocale = Locale.FRENCH;
        Locale currentLocale = Locale.ENGLISH;
        when(context.getLocale()).thenReturn(currentLocale);

        Callable<String> callable = () -> {
            throw new Exception("test exception");
        };

        // When
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            baseFaker.doWith(callable, newLocale);
        });

        // Then
        assertEquals("java.lang.Exception: test exception", exception.getMessage());
        verify(context).setCurrentLocale(newLocale);
        verify(fakeValuesService).updateFakeValuesInterfaceMap(anyList());
        verify(context).setCurrentLocale(currentLocale);
        verify(fakeValuesService, times(2)).updateFakeValuesInterfaceMap(anyList());
    }
}
