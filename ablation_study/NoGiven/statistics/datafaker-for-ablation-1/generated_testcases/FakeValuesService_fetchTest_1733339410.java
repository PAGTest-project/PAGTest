
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_fetchTest {
    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        context = new FakerContext(Locale.ENGLISH, new RandomService());
        fakeValuesService = new FakeValuesService();
    }

    @Test
    public void testFetchWithListOfOneElement() {
        fakeValuesService.addPath(Locale.ENGLISH, Path.of("src/test/resources/en.yml"));
        Object result = fakeValuesService.fetch("address.default_country", context);
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testFetchWithListOfMultipleElements() {
        fakeValuesService.addPath(Locale.US, Path.of("src/test/resources/en_US.yml"));
        Object result = fakeValuesService.fetch("address.default_country", new FakerContext(Locale.US, new RandomService()));
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testFetchWithEmptyList() {
        fakeValuesService.addPath(Locale.ENGLISH, Path.of("src/test/resources/empty.yml"));
        Object result = fakeValuesService.fetch("address.default_country", context);
        assertNull(result);
    }

    @Test
    public void testFetchWithNonListObject() {
        fakeValuesService.addPath(Locale.ENGLISH, Path.of("src/test/resources/non_list.yml"));
        Object result = fakeValuesService.fetch("address.default_country", context);
        assertNotNull(result);
        assertFalse(result instanceof List);
    }
}
