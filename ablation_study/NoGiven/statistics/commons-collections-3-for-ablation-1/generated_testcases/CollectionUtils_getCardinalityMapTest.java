
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class CollectionUtils_getCardinalityMapTest {

    @Test
    void testGetCardinalityMap() {
        Iterable<String> coll = Arrays.asList("a", "b", "a", "c", "b", "a");
        Map<String, Integer> result = CollectionUtils.getCardinalityMap(coll);

        assertEquals(3, result.get("a"));
        assertEquals(2, result.get("b"));
        assertEquals(1, result.get("c"));
        assertNull(result.get("d"));
    }

    @Test
    void testGetCardinalityMap_NullInput() {
        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.getCardinalityMap(null);
        });
    }
}
