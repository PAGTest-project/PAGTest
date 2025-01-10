
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FakeValuesService_safeFetchTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        context = new FakerContext(Locale.ENGLISH, new RandomService());
        fakeValuesService = new FakeValuesService();
    }

    @Test
    public void testSafeFetchWithNullObject() {
        String result = fakeValuesService.safeFetch("nonExistentKey", context, "defaultIfNull");
        assertEquals("defaultIfNull", result);
    }

    @Test
    public void testSafeFetchWithSingleStringList() {
        List<String> values = List.of("singleValue");
        context.getLocaleChain().get(0).getFakeValuesInterfaceMap().put("testKey", values);
        String result = fakeValuesService.safeFetch("testKey", context, "defaultIfNull");
        assertEquals("singleValue", result);
    }

    @Test
    public void testSafeFetchWithMultipleStringList() {
        List<String> values = List.of("value1", "value2", "value3");
        context.getLocaleChain().get(0).getFakeValuesInterfaceMap().put("testKey", values);
        String result = fakeValuesService.safeFetch("testKey", context, "defaultIfNull");
        assertNotNull(result);
    }

    @Test
    public void testSafeFetchWithSlashDelimitedRegex() {
        String regex = "/[a-z]+/";
        context.getLocaleChain().get(0).getFakeValuesInterfaceMap().put("testKey", regex);
        String result = fakeValuesService.safeFetch("testKey", context, "defaultIfNull");
        assertEquals("#{regexify '%s'}".formatted(regex.substring(1, regex.length() - 1)), result);
    }

    @Test
    public void testSafeFetchWithNonStringObject() {
        Integer nonStringObject = 12345;
        context.getLocaleChain().get(0).getFakeValuesInterfaceMap().put("testKey", nonStringObject);
        String result = fakeValuesService.safeFetch("testKey", context, "defaultIfNull");
        assertEquals("12345", result);
    }
}
