
package org.apache.commons.collections4.list;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GrowthList_addTest {

    @Test
    public void testAddWithGrowth() {
        List<String> list = new ArrayList<>();
        GrowthList<String> growthList = new GrowthList<>(list);

        growthList.add(5, "element");

        assertEquals(6, growthList.size());
        assertEquals(null, growthList.get(0));
        assertEquals(null, growthList.get(1));
        assertEquals(null, growthList.get(2));
        assertEquals(null, growthList.get(3));
        assertEquals(null, growthList.get(4));
        assertEquals("element", growthList.get(5));
    }
}
