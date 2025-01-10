
package net.datafaker.service;

import net.datafaker.internal.helper.SingletonLocale;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FakeValuesService_fetchObjectTest {

    @Test
    public void testFetchObject_SingleLocale_FoundInCache() {
        // Given
        FakeValuesService fakeValuesService = new FakeValuesService();
        FakerContext context = mock(FakerContext.class);
        SingletonLocale locale = SingletonLocale.get(Locale.ENGLISH);
        when(context.getLocaleChain()).thenReturn(Collections.singletonList(locale));

        Map<String, Object> mockMap = mock(Map.class);
        when(mockMap.get("key")).thenReturn("value");
        fakeValuesService.key2fetchedObject.put(locale, mockMap);

        // When
        String result = fakeValuesService.fetchObject("key", context);

        // Then
        assertEquals("value", result);
    }

    @Test
    public void testFetchObject_MultipleLocales_FoundInFakeValuesInterfaceMap() {
        // Given
        FakeValuesService fakeValuesService = new FakeValuesService();
        FakerContext context = mock(FakerContext.class);
        SingletonLocale locale1 = SingletonLocale.get(Locale.ENGLISH);
        SingletonLocale locale2 = SingletonLocale.get(Locale.FRENCH);
        when(context.getLocaleChain()).thenReturn(List.of(locale1, locale2));

        FakeValuesInterface fakeValuesInterface = mock(FakeValuesInterface.class);
        when(fakeValuesInterface.get("key")).thenReturn("value");
        fakeValuesService.fakeValuesInterfaceMap.put(locale2, fakeValuesInterface);

        // When
        String result = fakeValuesService.fetchObject("key", context);

        // Then
        assertEquals("value", result);
    }

    @Test
    public void testFetchObject_MultipleLocales_DefaultLocaleExcluded() {
        // Given
        FakeValuesService fakeValuesService = new FakeValuesService();
        FakerContext context = mock(FakerContext.class);
        SingletonLocale locale1 = SingletonLocale.get(Locale.ENGLISH);
        SingletonLocale locale2 = SingletonLocale.get(Locale.FRENCH);
        when(context.getLocaleChain()).thenReturn(List.of(locale1, locale2));

        Map<String, Object> mockMap = mock(Map.class);
        when(mockMap.get("key")).thenReturn("value");
        fakeValuesService.key2fetchedObject.put(locale2, mockMap);

        // When
        String result = fakeValuesService.fetchObject("key", context);

        // Then
        assertEquals("value", result);
    }

    @Test
    public void testFetchObject_ResultIsList_ProcessedCorrectly() {
        // Given
        FakeValuesService fakeValuesService = new FakeValuesService();
        FakerContext context = mock(FakerContext.class);
        SingletonLocale locale = SingletonLocale.get(Locale.ENGLISH);
        when(context.getLocaleChain()).thenReturn(Collections.singletonList(locale));

        List<String> listResult = List.of("item1", "item2");
        FakeValuesInterface fakeValuesInterface = mock(FakeValuesInterface.class);
        when(fakeValuesInterface.get("key")).thenReturn(listResult);
        fakeValuesService.fakeValuesInterfaceMap.put(locale, fakeValuesInterface);

        // When
        List<?> result = fakeValuesService.fetchObject("key", context);

        // Then
        assertEquals(listResult, result);
    }
}
