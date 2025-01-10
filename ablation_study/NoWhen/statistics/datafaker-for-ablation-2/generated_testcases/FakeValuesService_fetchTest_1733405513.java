
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_fetchTest {
    private FakeValuesService fakeValuesService;
    private FakerContext enContext;
    private FakerContext enUsContext;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        enContext = new FakerContext(Locale.ENGLISH, null);
        enUsContext = new FakerContext(Locale.US, null);
    }

    @Test
    public void testFetchWithSingleElementList() {
        Object result = fakeValuesService.fetch("address.default_country", enContext);
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testFetchWithMultipleElementList() {
        Object result = fakeValuesService.fetch("address.default_country", enUsContext);
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testFetchWithEmptyList() {
        Object result = fakeValuesService.fetch("nonexistent.key", enContext);
        assertNull(result);
    }

    @Test
    public void testFetchWithNonListObject() {
        Object result = fakeValuesService.fetch("address.city_prefix", enContext);
        assertNotNull(result);
        assertTrue(result instanceof String);
    }
}
