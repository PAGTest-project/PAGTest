
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_fetchTest {
    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, new RandomService());
    }

    @Test
    public void testFetchWithListOfOneElement() {
        Object result = fakeValuesService.fetch("singleElementList", context);
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testFetchWithEmptyList() {
        Object result = fakeValuesService.fetch("emptyList", context);
        assertNull(result);
    }

    @Test
    public void testFetchWithMultipleElementsList() {
        Object result = fakeValuesService.fetch("multipleElementsList", context);
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testFetchWithNonListObject() {
        Object result = fakeValuesService.fetch("nonListObject", context);
        assertNull(result);
    }

    @Test
    public void testFetchWithNullKey() {
        Object result = fakeValuesService.fetch(null, context);
        assertNull(result);
    }

    @Test
    public void testFetchWithNullContext() {
        Object result = fakeValuesService.fetch("key", null);
        assertNull(result);
    }

    @Test
    public void testFetchWithInvalidKey() {
        Object result = fakeValuesService.fetch("invalidKey", context);
        assertNull(result);
    }
}
