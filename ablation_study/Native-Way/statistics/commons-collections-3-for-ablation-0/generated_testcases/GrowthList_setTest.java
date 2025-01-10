
package org.apache.commons.collections4.list;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrowthList_setTest {

    @Test
    public void testSetWithGrowth() {
        List<String> list = new ArrayList<>();
        GrowthList<String> growthList = new GrowthList<>(list);

        // Given
        int index = 5;
        String element = "newElement";

        // When
        String result = growthList.set(index, element);

        // Then
        assertEquals(null, result); // Since the list was empty, the result should be null
        assertEquals(element, growthList.get(index)); // The element should be set at the specified index
        assertEquals(6, growthList.size()); // The list should have grown to size 6
    }

    @Test
    public void testSetWithoutGrowth() {
        List<String> list = new ArrayList<>();
        list.add("element0");
        list.add("element1");
        GrowthList<String> growthList = new GrowthList<>(list);

        // Given
        int index = 1;
        String element = "newElement";

        // When
        String result = growthList.set(index, element);

        // Then
        assertEquals("element1", result); // The previous element at index 1 should be returned
        assertEquals(element, growthList.get(index)); // The element should be set at the specified index
        assertEquals(2, growthList.size()); // The list size should remain unchanged
    }
}
