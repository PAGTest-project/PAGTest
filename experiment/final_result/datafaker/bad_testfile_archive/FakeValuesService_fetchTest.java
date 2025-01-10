
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.*;

public class FakeValuesService_fetchTest {

    private FakeValuesService fakeValuesService;
    private FakerContext context;

    @BeforeEach
    public void setUp() {
        fakeValuesService = new FakeValuesService();
        context = new FakerContext(Locale.ENGLISH, null);
    }

    @Test
    public void testFetchWithListOfOneElement() {
        List<String> singleElementList = Arrays.asList("singleElement");
        fakeValuesService.addUrl(Locale.ENGLISH, FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, null)).getUrl());
        Object result = fakeValuesService.fetch("key", context);
        assertEquals(singleElementList.get(0), result);
    }

    @Test
    public void testFetchWithEmptyList() {
        List<String> emptyList = Arrays.asList();
        fakeValuesService.addUrl(Locale.ENGLISH, FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, null)).getUrl());
        Object result = fakeValuesService.fetch("key", context);
        assertNull(result);
    }

    @Test
    public void testFetchWithMultipleElementsList() {
        List<String> multipleElementsList = Arrays.asList("element1", "element2", "element3");
        fakeValuesService.addUrl(Locale.ENGLISH, FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, null)).getUrl());
        Object result = fakeValuesService.fetch("key", context);
        assertTrue(multipleElementsList.contains(result));
    }

    @Test
    public void testFetchWithNonListObject() {
        Object nonListObject = "nonListObject";
        fakeValuesService.addUrl(Locale.ENGLISH, FakeValues.of(FakeValuesContext.of(Locale.ENGLISH, null)).getUrl());
        Object result = fakeValuesService.fetch("key", context);
        assertNull(result);
    }
}
