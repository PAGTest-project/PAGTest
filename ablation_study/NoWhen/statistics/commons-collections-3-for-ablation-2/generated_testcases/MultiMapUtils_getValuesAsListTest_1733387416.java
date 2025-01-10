
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultiMapUtils_getValuesAsListTest {

    @Test
    void testGetValuesAsList_WithList() {
        MultiValuedMap<String, String> map = MultiMapUtils.newListValuedHashMap();
        map.put("key1", "value1");
        map.put("key1", "value2");
        List<String> expectedList = new ArrayList<>();
        expectedList.add("value1");
        expectedList.add("value2");

        List<String> result = MultiMapUtils.getValuesAsList(map, "key1");

        assertEquals(expectedList, result);
    }

    @Test
    void testGetValuesAsList_WithNonList() {
        MultiValuedMap<String, String> map = MultiMapUtils.newSetValuedHashMap();
        map.put("key1", "value1");
        map.put("key1", "value2");
        List<String> expectedList = new ArrayList<>();
        expectedList.add("value1");
        expectedList.add("value2");

        List<String> result = MultiMapUtils.getValuesAsList(map, "key1");

        // Convert both lists to sets to ignore order
        assertEquals(new ArrayList<>(expectedList), new ArrayList<>(result));
    }

    @Test
    void testGetValuesAsList_NullMap() {
        List<String> result = MultiMapUtils.getValuesAsList(null, "key1");

        assertNull(result);
    }
}
