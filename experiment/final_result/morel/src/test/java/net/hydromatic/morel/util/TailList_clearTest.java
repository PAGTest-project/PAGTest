
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TailList_clearTest {

    @Test
    public void testClear() {
        List<Integer> backingList = new ArrayList<>();
        backingList.add(1);
        backingList.add(2);
        backingList.add(3);

        TailList<Integer> tailList = new TailList<>(backingList, 1);
        tailList.clear();

        assertEquals(1, backingList.size());
        assertEquals(Integer.valueOf(1), backingList.get(0));
    }
}
