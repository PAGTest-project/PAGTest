
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
        String result = fakeValuesService.safeFetch("nonexistent_key", context, "default");
        assertEquals("default", result);
    }

    @Test
    public void testSafeFetchWithSingleString() {
        String result = fakeValuesService.safeFetch("single_string_key", context, "default");
        assertNotNull(result);
    }

    @Test
    public void testSafeFetchWithListOfStrings() {
        String result = fakeValuesService.safeFetch("list_of_strings_key", context, "default");
        assertNotNull(result);
    }

    @Test
    public void testSafeFetchWithSlashDelimitedRegex() {
        String result = fakeValuesService.safeFetch("regex_key", context, "default");
        assertNotNull(result);
    }

    @Test
    public void testSafeFetchWithEmptyList() {
        String result = fakeValuesService.safeFetch("empty_list_key", context, "default");
        assertEquals("default", result);
    }

    @Test
    public void testSafeFetchWithNonStringObject() {
        String result = fakeValuesService.safeFetch("non_string_object_key", context, "default");
        assertNotNull(result);
    }
}
