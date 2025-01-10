
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
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
    public void testFetchWithSingleElementList() {
        List<String> singleElementList = List.of("singleElement");
        Object result = fakeValuesService.fetch("key", context);
        assertEquals(singleElementList.get(0), result);
    }

    @Test
    public void testFetchWithMultipleElementList() {
        List<String> multipleElementList = List.of("element1", "element2", "element3");
        Object result = fakeValuesService.fetch("key", context);
        assertTrue(multipleElementList.contains(result));
    }

    @Test
    public void testFetchWithEmptyList() {
        List<String> emptyList = List.of();
        Object result = fakeValuesService.fetch("key", context);
        assertNull(result);
    }

    @Test
    public void testFetchWithNonListObject() {
        Object nonListObject = "notAList";
        Object result = fakeValuesService.fetch("key", context);
        assertNull(result);
    }

    @Test
    public void testFetchWithNullObject() {
        Object result = fakeValuesService.fetch("key", context);
        assertNull(result);
    }
}
