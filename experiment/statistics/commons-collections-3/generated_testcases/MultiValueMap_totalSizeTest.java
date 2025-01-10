
package org.apache.commons.collections4.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiValueMap_totalSizeTest {

    private MultiValueMap<String, String> multiValueMap;

    @BeforeEach
    public void setUp() {
        multiValueMap = new MultiValueMap<>();
    }

    @Test
    public void testTotalSize() {
        // Given
        Map<String, List<String>> initialMap = new HashMap<>();
        List<String> listA = new ArrayList<>();
        listA.add("A1");
        listA.add("A2");
        initialMap.put("A", listA);

        List<String> listB = new ArrayList<>();
        listB.add("B1");
        listB.add("B2");
        listB.add("B3");
        initialMap.put("B", listB);

        multiValueMap.putAll(initialMap);

        // When
        int totalSize = multiValueMap.totalSize();

        // Then
        assertEquals(5, totalSize);
    }

    @Test
    public void testTotalSizeAfterAddingElements() {
        // Given
        multiValueMap.put("A", "A1");
        multiValueMap.put("A", "A2");
        multiValueMap.put("B", "B1");
        multiValueMap.put("B", "B2");
        multiValueMap.put("B", "B3");

        // When
        int totalSize = multiValueMap.totalSize();

        // Then
        assertEquals(5, totalSize);
    }

    @Test
    public void testTotalSizeAfterRemovingElements() {
        // Given
        multiValueMap.put("A", "A1");
        multiValueMap.put("A", "A2");
        multiValueMap.put("B", "B1");
        multiValueMap.put("B", "B2");
        multiValueMap.put("B", "B3");

        multiValueMap.removeMapping("A", "A1");
        multiValueMap.removeMapping("B", "B2");

        // When
        int totalSize = multiValueMap.totalSize();

        // Then
        assertEquals(3, totalSize);
    }
}
