
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FakeValuesGrouping_addTest {

    @Test
    void testAddWithFakeValues() {
        // Given
        FakeValuesContext context = FakeValuesContext.of(Locale.ENGLISH, "file", "path");
        FakeValues fakeValue = FakeValues.of(context);
        Set<String> paths = Set.of("path1", "path2");
        // Mocking getPaths to return predefined paths
        fakeValue.getPaths = () -> paths;

        FakeValuesGrouping grouping = new FakeValuesGrouping();

        // When
        grouping.add(fakeValue);

        // Then
        for (String path : paths) {
            assertTrue(grouping.fakeValues.containsKey(path));
            assertTrue(grouping.fakeValues.get(path).contains(fakeValue));
        }
    }

    @Test
    void testAddWithFakeValuesGrouping() {
        // Given
        FakeValuesGrouping grouping1 = new FakeValuesGrouping();
        FakeValuesGrouping grouping2 = new FakeValuesGrouping();

        // When
        grouping1.add(grouping2);

        // Then
        assertEquals(grouping2.fakeValues, grouping1.fakeValues);
    }

    @Test
    void testAddWithUnsupportedType() {
        // Given
        FakeValuesGrouping grouping = new FakeValuesGrouping();
        FakeValuesInterface unsupportedValue = new FakeValuesInterface() {};

        // When & Then
        assertThrows(RuntimeException.class, () -> grouping.add(unsupportedValue));
    }
}
