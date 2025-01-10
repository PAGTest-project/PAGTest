
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class FakeValuesGrouping_getTest {

    @Test
    public void testGet_WithSingleFakeValue() {
        // Given
        FakeValuesInterface fakeValue = mock(FakeValuesInterface.class);
        Map<String, Object> fakeMap = new HashMap<>();
        fakeMap.put("key1", "value1");
        when(fakeValue.get("key1")).thenReturn(fakeMap);

        FakeValuesGrouping fakeValuesGrouping = new FakeValuesGrouping();
        fakeValuesGrouping.add(fakeValue);

        // When
        Map result = fakeValuesGrouping.get("key1");

        // Then
        assertEquals(fakeMap, result);
    }

    @Test
    public void testGet_WithMultipleFakeValues() {
        // Given
        FakeValuesInterface fakeValue1 = mock(FakeValuesInterface.class);
        FakeValuesInterface fakeValue2 = mock(FakeValuesInterface.class);

        Map<String, Object> fakeMap1 = new HashMap<>();
        fakeMap1.put("key1", "value1");
        when(fakeValue1.get("key1")).thenReturn(fakeMap1);

        Map<String, Object> fakeMap2 = new HashMap<>();
        fakeMap2.put("key1", "value2");
        when(fakeValue2.get("key1")).thenReturn(fakeMap2);

        FakeValuesGrouping fakeValuesGrouping = new FakeValuesGrouping();
        fakeValuesGrouping.add(fakeValue1);
        fakeValuesGrouping.add(fakeValue2);

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
        FakeValuesGrouping fakeValuesGrouping = new FakeValuesGrouping();

        // When
        Map result = fakeValuesGrouping.get("nonexistentKey");

        // Then
        assertNull(result);
    }
}
