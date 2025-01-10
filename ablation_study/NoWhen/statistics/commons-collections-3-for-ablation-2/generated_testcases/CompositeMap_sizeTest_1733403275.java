
package org.apache.commons.collections4.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeMap_sizeTest {

    private CompositeMap<String, String> compositeMap;
    private Map<String, String> mapOne;
    private Map<String, String> mapTwo;

    @BeforeEach
    public void setUp() {
        mapOne = new HashMap<>();
        mapOne.put("1", "one");
        mapOne.put("2", "two");

        mapTwo = new HashMap<>();
        mapTwo.put("3", "three");
        mapTwo.put("4", "four");

        compositeMap = new CompositeMap<>(mapOne, mapTwo);
    }

    @Test
    public void testSizeWithTwoMaps() {
        assertEquals(4, compositeMap.size());
    }

    @Test
    public void testSizeAfterAddingMap() {
        Map<String, String> mapThree = new HashMap<>();
        mapThree.put("5", "five");
        mapThree.put("6", "six");

        compositeMap.addComposited(mapThree);
        assertEquals(6, compositeMap.size());
    }

    @Test
    public void testSizeAfterRemovingMap() {
        compositeMap.removeComposited(mapTwo);
        assertEquals(2, compositeMap.size());
    }

    @Test
    public void testSizeWithEmptyCompositeMap() {
        CompositeMap<String, String> emptyCompositeMap = new CompositeMap<>();
        assertEquals(0, emptyCompositeMap.size());
    }

    @Test
    public void testSizeWithSingleMap() {
        CompositeMap<String, String> singleMapComposite = new CompositeMap<>(mapOne);
        assertEquals(2, singleMapComposite.size());
    }

    @Test
    public void testSizeAfterClear() {
        compositeMap.clear();
        assertEquals(0, compositeMap.size());
    }

    @Test
    public void testSizeAfterPartialClear() {
        mapOne.clear();
        assertEquals(2, compositeMap.size());
    }

    @Test
    public void testSizeWithDuplicateKeys() {
        Map<String, String> duplicateKeyMap = new HashMap<>();
        duplicateKeyMap.put("1", "duplicateOne");
        compositeMap.addComposited(duplicateKeyMap);
        assertEquals(4, compositeMap.size()); // Size should remain the same due to key collision
    }
}
