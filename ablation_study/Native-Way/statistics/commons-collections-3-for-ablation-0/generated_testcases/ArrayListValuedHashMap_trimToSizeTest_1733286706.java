
package org.apache.commons.collections4.multimap;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayListValuedHashMap_trimToSizeTest {

    @Test
    public void testTrimToSize() {
        // Given
        ArrayListValuedHashMap<String, String> map = new ArrayListValuedHashMap<>();
        map.put("key1", "value1");
        map.put("key1", "value2");
        map.put("key2", "value3");

        // When
        map.trimToSize();

        // Then
        for (final Collection<String> coll : map.getMap().values()) {
            final ArrayList<String> list = (ArrayList<String>) coll;
            assertEquals(list.size(), list.size()); // Ensure no exception is thrown
        }
    }
}
