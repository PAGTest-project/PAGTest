
package net.datafaker.providers.base;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Unique_fetchFromYamlTest {

    private Unique unique;
    private BaseProviders faker;
    private Locale testLocale;

    @BeforeEach
    void setUp() {
        faker = mock(BaseProviders.class);
        unique = new Unique(faker);
        testLocale = Locale.US;
        when(faker.getContext().getLocale()).thenReturn(testLocale);
    }

    @Test
    void testFetchFromYaml_ValuesAvailable() {
        String key = "testKey";
        List<String> values = new ArrayList<>(Arrays.asList("value1", "value2", "value3"));

        when(faker.fakeValuesService().fetchObject(key, faker.getContext())).thenReturn(values);

        String result = unique.fetchFromYaml(key);

        assertNotNull(result);
        assertFalse(values.contains(result));
    }

    @Test
    void testFetchFromYaml_NoValuesAvailable() {
        String key = "testKey";
        List<String> values = new ArrayList<>();

        when(faker.fakeValuesService().fetchObject(key, faker.getContext())).thenReturn(values);

        assertThrows(NoSuchElementException.class, () -> unique.fetchFromYaml(key));
    }

    @Test
    void testFetchFromYaml_ValuesExhausted() {
        String key = "testKey";
        List<String> values = new ArrayList<>(Arrays.asList("value1"));

        when(faker.fakeValuesService().fetchObject(key, faker.getContext())).thenReturn(values);

        // First call should return the only value
        String result1 = unique.fetchFromYaml(key);
        assertNotNull(result1);

        // Second call should throw NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> unique.fetchFromYaml(key));
    }
}
