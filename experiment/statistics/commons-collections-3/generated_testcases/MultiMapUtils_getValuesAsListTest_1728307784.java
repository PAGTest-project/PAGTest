
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MultiMapUtils_getValuesAsListTest {

    @Test
    void testGetValuesAsList_WithList() {
        MultiValuedMap<String, String> map = new ArrayListValuedHashMap<>();
        List<String> list = new ArrayList<>();
        list.add("value1");
        list.add("value2");
        map.put("key", list);

        List<String> result = MultiMapUtils.getValuesAsList(map, "key");
        assertEquals(list, result);
    }

    @Test
    void testGetValuesAsList_WithNonList() {
        MultiValuedMap<String, String> map = new HashSetValuedHashMap<>();
        Collection<String> collection = new ArrayList<>();
        collection.add("value1");
        collection.add("value2");
        map.put("key", collection);

        List<String> result = MultiMapUtils.getValuesAsList(map, "key");
        assertEquals(new ArrayList<>(collection), result);
    }

    @Test
    void testGetValuesAsList_WithNullMap() {
        List<String> result = MultiMapUtils.getValuesAsList(null, "key");
        assertNull(result);
    }
}
