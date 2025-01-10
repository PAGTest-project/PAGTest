
package net.datafaker.service;

import net.datafaker.internal.helper.SingletonLocale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class FakeValuesService_fetchObjectTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;
    private Map<SingletonLocale, Map<String, Object>> key2fetchedObject;
    private Map<SingletonLocale, FakeValuesInterface> fakeValuesInterfaceMap;

    @BeforeEach
    void setUp() {
        fakeValuesService = spy(new FakeValuesService());
        context = mock(FakerContext.class);
        key2fetchedObject = mock(Map.class);
        fakeValuesInterfaceMap = mock(Map.class);

        when(context.getLocaleChain()).thenReturn(List.of(SingletonLocale.get(Locale.ENGLISH)));
        doReturn(key2fetchedObject).when(fakeValuesService).key2fetchedObject;
        doReturn(fakeValuesInterfaceMap).when(fakeValuesService).fakeValuesInterfaceMap;
    }

    @Test
    void testFetchObject_FoundInCache() {
        String key = "testKey";
        SingletonLocale locale = SingletonLocale.get(Locale.ENGLISH);
        Map<String, Object> stringObjectMap = mock(Map.class);
        Object expectedResult = new Object();

        when(key2fetchedObject.get(locale)).thenReturn(stringObjectMap);
        when(stringObjectMap.get(key)).thenReturn(expectedResult);

        Object result = fakeValuesService.fetchObject(key, context);

        assertEquals(expectedResult, result);
    }

    @Test
    void testFetchObject_FoundInFakeValuesInterface() {
        String key = "testKey";
        SingletonLocale locale = SingletonLocale.get(Locale.ENGLISH);
        Map<String, Object> stringObjectMap = mock(Map.class);
        FakeValuesInterface fakeValuesInterface = mock(FakeValuesInterface.class);
        Object expectedResult = new Object();

        when(key2fetchedObject.get(locale)).thenReturn(stringObjectMap);
        when(stringObjectMap.get(key)).thenReturn(null);
        when(fakeValuesInterfaceMap.get(locale)).thenReturn(fakeValuesInterface);
        when(fakeValuesInterface.get(anyString())).thenReturn(expectedResult);

        Object result = fakeValuesService.fetchObject(key, context);

        assertEquals(expectedResult, result);
    }

    @Test
    void testFetchObject_NotFound() {
        String key = "testKey";
        SingletonLocale locale = SingletonLocale.get(Locale.ENGLISH);
        Map<String, Object> stringObjectMap = mock(Map.class);
        FakeValuesInterface fakeValuesInterface = mock(FakeValuesInterface.class);

        when(key2fetchedObject.get(locale)).thenReturn(stringObjectMap);
        when(stringObjectMap.get(key)).thenReturn(null);
        when(fakeValuesInterfaceMap.get(locale)).thenReturn(fakeValuesInterface);
        when(fakeValuesInterface.get(anyString())).thenReturn(null);

        Object result = fakeValuesService.fetchObject(key, context);

        assertEquals(null, result);
    }
}
