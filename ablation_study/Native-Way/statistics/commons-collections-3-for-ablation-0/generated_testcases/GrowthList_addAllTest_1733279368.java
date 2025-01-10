
package org.apache.commons.collections4.list;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GrowthList_addAllTest {

    @Test
    void testAddAll_GrowthBehavior() {
        GrowthList<String> growthList = new GrowthList<>();
        List<String> collection = Arrays.asList("A", "B");

        // Test case: index > size
        assertTrue(growthList.addAll(5, collection));
        assertEquals(Arrays.asList(null, null, null, null, null, "A", "B"), growthList);

        // Test case: index <= size
        assertTrue(growthList.addAll(2, Collections.singletonList("C")));
        assertEquals(Arrays.asList(null, null, "C", null, null, "A", "B"), growthList);
    }
}
