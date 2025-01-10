
package org.apache.commons.collections4.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.AbstractMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SortedProperties_entrySetTest {

    private SortedProperties sortedProperties;

    @BeforeEach
    public void setUp() {
        sortedProperties = new SortedProperties();
    }

    @Test
    public void testEntrySet() {
        for (char ch = 'Z'; ch >= 'A'; ch--) {
            sortedProperties.put(String.valueOf(ch), "Value" + ch);
        }

        final Set<Map.Entry<Object, Object>> entrySet = sortedProperties.entrySet();
        final Set<Map.Entry<Object, Object>> expectedEntrySet = sortedProperties.keySet().stream()
                .map(k -> new AbstractMap.SimpleEntry<>(k, sortedProperties.getProperty((String) k)))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        assertEquals(expectedEntrySet.size(), entrySet.size());
        assertTrue(entrySet.containsAll(expectedEntrySet));
    }
}
