
package net.datafaker.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FakeValuesGrouping_getTest {

    private FakeValuesGrouping fakeValuesGrouping;
    private FakeValuesInterface fakeValuesMock;

    @BeforeEach
    public void setUp() {
        fakeValuesGrouping = new FakeValuesGrouping();
        fakeValuesMock = mock(FakeValuesInterface.class);
    }

    @Test
    public void testGet_WithSingleFakeValue() {
        // Given
        Map<String, Object> mockMap = new HashMap<>();
        mockMap.put("key1", "value1");
        when(fakeValuesMock.get("key1")).thenReturn(mockMap);
        fakeValuesGrouping.add(fakeValuesMock);

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
        when(fakeValuesMock.get("key1")).thenReturn(mockMap1).thenReturn(mockMap2);
        fakeValuesGrouping.add(fakeValuesMock);
        fakeValuesGrouping.add(fakeValuesMock);

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
        // No fake values added

        // When
        Map result = fakeValuesGrouping.get("key1");

        // Then
        assertNull(result);
    }
}
