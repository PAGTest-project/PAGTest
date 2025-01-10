
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
    private SingletonLocale locale1;
    private SingletonLocale locale2;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        context = mock(FakerContext.class);
        locale1 = SingletonLocale.get(Locale.ENGLISH);
        locale2 = SingletonLocale.get(Locale.FRENCH);
    }

    @Test
    public void testFetchObject_SingleLocale_FoundInCache() {
        // Given
        String key = "testKey";
        Map<String, Object> cacheMap = Map.of(key, "cachedValue");
        fakeValuesService.key2fetchedObject.put(locale1, cacheMap);
        when(context.getLocaleChain()).thenReturn(List.of(locale1));

        // When
        String result = fakeValuesService.fetchObject(key, context);

        // Then
        assertEquals("cachedValue", result);
    }

    @Test
    public void testFetchObject_MultipleLocales_FoundInSecondLocale() {
        // Given
        String key = "testKey";
        Map<String, Object> cacheMap1 = Collections.emptyMap();
        Map<String, Object> cacheMap2 = Map.of(key, "secondLocaleValue");
        fakeValuesService.key2fetchedObject.put(locale1, cacheMap1);
        fakeValuesService.key2fetchedObject.put(locale2, cacheMap2);
        when(context.getLocaleChain()).thenReturn(List.of(locale1, locale2));

        // When
        String result = fakeValuesService.fetchObject(key, context);

        // Then
        assertEquals("secondLocaleValue", result);
    }

    @Test
    public void testFetchObject_DefaultLocaleExcluded_FoundInSecondLocale() {
        // Given
        String key = "testKey";
        Map<String, Object> cacheMap1 = Collections.emptyMap();
        Map<String, Object> cacheMap2 = Map.of(key, "secondLocaleValue");
        fakeValuesService.key2fetchedObject.put(FakeValuesService.DEFAULT_LOCALE, cacheMap1);
        fakeValuesService.key2fetchedObject.put(locale2, cacheMap2);
        when(context.getLocaleChain()).thenReturn(List.of(FakeValuesService.DEFAULT_LOCALE, locale2));

        // When
        String result = fakeValuesService.fetchObject(key, context);

        // Then
        assertEquals("secondLocaleValue", result);
    }

    @Test
    public void testFetchObject_NoValueFound_ReturnsNull() {
        // Given
        String key = "testKey";
        Map<String, Object> cacheMap = Collections.emptyMap();
        fakeValuesService.key2fetchedObject.put(locale1, cacheMap);
        when(context.getLocaleChain()).thenReturn(List.of(locale1));

        // When
        String result = fakeValuesService.fetchObject(key, context);

        // Then
        assertEquals(null, result);
    }
}
