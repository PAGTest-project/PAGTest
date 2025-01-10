
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
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, new RandomService());
    }

    @Test
    public void testSafeFetchWithNullObject() {
        String result = fakeValuesService.safeFetch("nonexistent_key", context, "default");
        assertEquals("default", result);
    }

    @Test
    public void testSafeFetchWithSingleElementList() {
        List<String> values = List.of("singleValue");
        context.getRandomService().setSeed(0); // Ensure deterministic behavior
        String result = fakeValuesService.safeFetch("key", context, "default");
        assertEquals("singleValue", result);
    }

    @Test
    public void testSafeFetchWithMultipleElementList() {
        List<String> values = List.of("value1", "value2", "value3");
        context.getRandomService().setSeed(1); // Ensure deterministic behavior
        String result = fakeValuesService.safeFetch("key", context, "default");
        assertEquals("value2", result);
    }

    @Test
    public void testSafeFetchWithSlashDelimitedRegex() {
        String regex = "/[a-z]+/";
        String result = fakeValuesService.safeFetch("key", context, "default");
        assertTrue(result.startsWith("#{regexify '"));
        assertTrue(result.endsWith("'}"));
    }

    @Test
    public void testSafeFetchWithNonListNonRegexObject() {
        String value = "simpleString";
        String result = fakeValuesService.safeFetch("key", context, "default");
        assertEquals("simpleString", result);
    }
}
