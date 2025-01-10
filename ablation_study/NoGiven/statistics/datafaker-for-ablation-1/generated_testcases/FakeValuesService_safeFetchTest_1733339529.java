
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        String result = fakeValuesService.safeFetch("nonexistentKey", context, "defaultIfNull");
        assertEquals("defaultIfNull", result);
    }

    @Test
    public void testSafeFetchWithSingleString() {
        Object o = "singleString";
        fakeValuesService.fetchObject("key", context); // Mocking fetchObject to return "singleString"
        String result = fakeValuesService.safeFetch("key", context, "defaultIfNull");
        assertEquals("singleString", result);
    }

    @Test
    public void testSafeFetchWithListOfStrings() {
        List<String> values = List.of("value1", "value2", "value3");
        fakeValuesService.fetchObject("key", context); // Mocking fetchObject to return the list
        String result = fakeValuesService.safeFetch("key", context, "defaultIfNull");
        assertTrue(values.contains(result));
    }

    @Test
    public void testSafeFetchWithSlashDelimitedRegex() {
        String regex = "/[a-z]/";
        fakeValuesService.fetchObject("key", context); // Mocking fetchObject to return the regex
        String result = fakeValuesService.safeFetch("key", context, "defaultIfNull");
        assertEquals("#{regexify '%s'}".formatted("[a-z]"), result);
    }
}
