
package net.datafaker.service;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FakeValuesGrouping_getTest {

    @Test
    public void testGetWithSingleFakeValue() {
        // Given
        FakeValuesInterface fakeValue = mock(FakeValuesInterface.class);
        Map<String, Object> fakeMap = new HashMap<>();
        fakeMap.put("key1", "value1");
        when(fakeValue.get("testKey")).thenReturn(fakeMap);

        FakeValuesGrouping fakeValuesGrouping = new FakeValuesGrouping();
        fakeValuesGrouping.add(fakeValue);

        // When
        Map result = fakeValuesGrouping.get("testKey");

        // Then
        assertEquals(fakeMap, result);
    }

    @Test
    public void testGetWithMultipleFakeValues() {
        // Given
        FakeValuesInterface fakeValue1 = mock(FakeValuesInterface.class);
        FakeValuesInterface fakeValue2 = mock(FakeValuesInterface.class);

        Map<String, Object> fakeMap1 = new HashMap<>();
        fakeMap1.put("key1", "value1");
        Map<String, Object> fakeMap2 = new HashMap<>();
        fakeMap2.put("key2", "value2");

        when(fakeValue1.get("testKey")).thenReturn(fakeMap1);
        when(fakeValue2.get("testKey")).thenReturn(fakeMap2);

        FakeValuesGrouping fakeValuesGrouping = new FakeValuesGrouping();
        fakeValuesGrouping.add(fakeValue1);
        fakeValuesGrouping.add(fakeValue2);

        // When
        Map result = fakeValuesGrouping.get("testKey");

        // Then
        Map<String, Object> expectedMap = new HashMap<>();
        expectedMap.putAll(fakeMap1);
        expectedMap.putAll(fakeMap2);
        assertEquals(expectedMap, result);
    }

    @Test
    public void testGetWithNoFakeValues() {
        // Given
        FakeValuesGrouping fakeValuesGrouping = new FakeValuesGrouping();

        // When
        Map result = fakeValuesGrouping.get("testKey");

        // Then
        assertEquals(null, result);
    }
}
