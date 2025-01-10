
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
        fakeValuesService = new FakeValuesService() {
            @Override
            public <T> T fetchObject(String key, FakerContext context) {
                if ("key".equals(key)) {
                    return (T) "singleString";
                } else if ("listKey".equals(key)) {
                    return (T) List.of("value1", "value2", "value3");
                } else if ("regexKey".equals(key)) {
                    return (T) "/[a-z]/";
                }
                return null;
            }
        };
    }

    @Test
    public void testSafeFetchWithNullObject() {
        String result = fakeValuesService.safeFetch("nonexistentKey", context, "defaultIfNull");
        assertEquals("defaultIfNull", result);
    }

    @Test
    public void testSafeFetchWithSingleString() {
        String result = fakeValuesService.safeFetch("key", context, "defaultIfNull");
        assertEquals("singleString", result);
    }

    @Test
    public void testSafeFetchWithListOfStrings() {
        String result = fakeValuesService.safeFetch("listKey", context, "defaultIfNull");
        assertTrue(List.of("value1", "value2", "value3").contains(result));
    }

    @Test
    public void testSafeFetchWithSlashDelimitedRegex() {
        String result = fakeValuesService.safeFetch("regexKey", context, "defaultIfNull");
        assertEquals("#{regexify '%s'}".formatted("[a-z]"), result);
    }
}
