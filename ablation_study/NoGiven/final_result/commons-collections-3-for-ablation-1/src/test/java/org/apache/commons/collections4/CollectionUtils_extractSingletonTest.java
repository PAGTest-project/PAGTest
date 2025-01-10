
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_extractSingletonTest {

    @Test
    void testExtractSingleton_withSingleElement() {
        List<String> collection = Collections.singletonList("element");
        String result = CollectionUtils.extractSingleton(collection);
        assertEquals("element", result);
    }

    @Test
    void testExtractSingleton_withEmptyCollection() {
        List<String> collection = Collections.emptyList();
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.extractSingleton(collection));
    }

    @Test
    void testExtractSingleton_withMultipleElements() {
        List<String> collection = Arrays.asList("element1", "element2");
        assertThrows(IllegalArgumentException.class, () -> CollectionUtils.extractSingleton(collection));
    }

    @Test
    void testExtractSingleton_withNullCollection() {
        assertThrows(NullPointerException.class, () -> CollectionUtils.extractSingleton(null));
    }
}
