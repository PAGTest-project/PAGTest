
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class FakeValuesGrouping_getTest {

    private FakeValuesGrouping fakeValuesGrouping;
    private FakeValuesInterface mockFakeValues;

    @BeforeEach
    public void setUp() {
        fakeValuesGrouping = new FakeValuesGrouping();
        mockFakeValues = mock(FakeValuesInterface.class);
    }

    @Test
    public void testGet_WithSingleFakeValue() {
        // Given
        Map<String, Object> mockMap = new HashMap<>();
        mockMap.put("key1", "value1");
        when(mockFakeValues.get("key1")).thenReturn(mockMap);
        fakeValuesGrouping.add(mockFakeValues);

        // When
        Map result = fakeValuesGrouping.get("key1");

        // Then
        assertEquals(mockMap, result);
    }

    @Test
    public void testGet_WithMultipleFakeValues() {
        // Given
        Map<String, Object> mockMap1 = new HashMap<>();
        mockMap1.put("key1", "value1");
        Map<String, Object> mockMap2 = new HashMap<>();
        mockMap2.put("key1", "value2");
        when(mockFakeValues.get("key1")).thenReturn(mockMap1, mockMap2);
        fakeValuesGrouping.add(mockFakeValues);

        // When
        Map result = fakeValuesGrouping.get("key1");

        // Then
        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.put("key1", "value2");
        assertEquals(expectedMap, result);
    }

    @Test
    public void testGet_WithNoFakeValues() {
        // Given
        when(mockFakeValues.get("key1")).thenReturn(null);
        fakeValuesGrouping.add(mockFakeValues);

        // When
        Map result = fakeValuesGrouping.get("key1");

        // Then
        assertNull(result);
    }

    @Test
    public void testGet_WithEmptyKey() {
        // Given
        when(mockFakeValues.get("")).thenReturn(Collections.emptyMap());
        fakeValuesGrouping.add(mockFakeValues);

        // When
        Map result = fakeValuesGrouping.get("");

        // Then
        assertEquals(Collections.emptyMap(), result);
    }
}
