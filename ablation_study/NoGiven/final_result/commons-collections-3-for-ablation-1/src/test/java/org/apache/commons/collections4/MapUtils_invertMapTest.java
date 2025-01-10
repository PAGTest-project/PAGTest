
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class MapUtils_invertMapTest {

    @Test
    public void testInvertMap_SuccessfulInversion() {
        // Given
        Map<String, Integer> inputMap = new HashMap<>();
        inputMap.put("one", 1);
        inputMap.put("two", 2);

        // When
        Map<Integer, String> invertedMap = MapUtils.invertMap(inputMap);

        // Then
        assertEquals(2, invertedMap.size());
        assertEquals("one", invertedMap.get(1));
        assertEquals("two", invertedMap.get(2));
    }

    @Test
    public void testInvertMap_NullMapThrowsException() {
        // Given
        Map<String, Integer> inputMap = null;

        // When & Then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            MapUtils.invertMap(inputMap);
        });
        assertEquals("map", exception.getMessage());
    }
}
