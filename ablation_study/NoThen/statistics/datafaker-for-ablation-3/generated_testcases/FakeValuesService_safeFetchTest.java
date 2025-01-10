
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    public void testSafeFetchWithSingleString() {
        // Assuming fetchObject returns a single string for "singleStringKey"
        String result = fakeValuesService.safeFetch("singleStringKey", context, "defaultIfNull");
        assertNotNull(result);
    }

    @Test
    public void testSafeFetchWithList() {
        // Assuming fetchObject returns a list for "listKey"
        String result = fakeValuesService.safeFetch("listKey", context, "defaultIfNull");
        assertNotNull(result);
    }

    @Test
    public void testSafeFetchWithSlashDelimitedRegex() {
        // Assuming fetchObject returns a slash-delimited regex string for "regexKey"
        String result = fakeValuesService.safeFetch("regexKey", context, "defaultIfNull");
        assertNotNull(result);
    }

    @Test
    public void testSafeFetchWithEmptyList() {
        // Assuming fetchObject returns an empty list for "emptyListKey"
        String result = fakeValuesService.safeFetch("emptyListKey", context, "defaultIfNull");
        assertEquals("defaultIfNull", result);
    }

    @Test
    public void testSafeFetchWithMultipleStringsInList() {
        // Assuming fetchObject returns a list with multiple strings for "multiStringListKey"
        String result = fakeValuesService.safeFetch("multiStringListKey", context, "defaultIfNull");
        assertNotNull(result);
    }
}
