
package net.datafaker.service;

import net.datafaker.internal.helper.SingletonLocale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FakeValuesService_fetchObjectTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;
    private Map<SingletonLocale, Map<String, Object>> key2fetchedObject;
    private Map<SingletonLocale, FakeValuesInterface> fakeValuesInterfaceMap;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        context = mock(FakerContext.class);
        key2fetchedObject = mock(Map.class);
        fakeValuesInterfaceMap = mock(Map.class);

        fakeValuesService.key2fetchedObject = key2fetchedObject;
        fakeValuesService.fakeValuesInterfaceMap = fakeValuesInterfaceMap;
    }

    @Test
    public void testFetchObject_SingleLocale_FoundInCache() {
        // Given
        String key = "testKey";
        SingletonLocale locale = SingletonLocale.get(Locale.ENGLISH);
        List<SingletonLocale> localeChain = Collections.singletonList(locale);
        Map<String, Object> stringObjectMap = mock(Map.class);
        Object expectedResult = new Object();

        when(context.getLocaleChain()).thenReturn(localeChain);
        when(key2fetchedObject.get(locale)).thenReturn(stringObjectMap);
        when(stringObjectMap.get(key)).thenReturn(expectedResult);

        // When
        Object result = fakeValuesService.fetchObject(key, context);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFetchObject_MultipleLocales_FoundInInterfaceMap() {
        // Given
        String key = "testKey";
        SingletonLocale locale1 = SingletonLocale.get(Locale.ENGLISH);
        SingletonLocale locale2 = SingletonLocale.get(Locale.FRENCH);
        List<SingletonLocale> localeChain = List.of(locale1, locale2);
        Map<String, Object> stringObjectMap = mock(Map.class);
        FakeValuesInterface fakeValuesInterface = mock(FakeValuesInterface.class);
        Object expectedResult = new Object();

        when(context.getLocaleChain()).thenReturn(localeChain);
        when(key2fetchedObject.get(locale1)).thenReturn(stringObjectMap);
        when(stringObjectMap.get(key)).thenReturn(null);
        when(fakeValuesInterfaceMap.get(locale2)).thenReturn(fakeValuesInterface);
        when(fakeValuesInterface.get(anyString())).thenReturn(expectedResult);

        // When
        Object result = fakeValuesService.fetchObject(key, context);

        // Then
        assertEquals(expectedResult, result);
    }

    @Test
    public void testFetchObject_ResultIsList_ProcessedCorrectly() {
        // Given
        String key = "testKey";
        SingletonLocale locale = SingletonLocale.get(Locale.ENGLISH);
        List<SingletonLocale> localeChain = Collections.singletonList(locale);
        Map<String, Object> stringObjectMap = mock(Map.class);
        List<Object> expectedResult = List.of("item1", "item2");

        when(context.getLocaleChain()).thenReturn(localeChain);
        when(key2fetchedObject.get(locale)).thenReturn(stringObjectMap);
        when(stringObjectMap.get(key)).thenReturn(expectedResult);

        // When
        Object result = fakeValuesService.fetchObject(key, context);

        // Then
        assertEquals(expectedResult, result);
    }
}
