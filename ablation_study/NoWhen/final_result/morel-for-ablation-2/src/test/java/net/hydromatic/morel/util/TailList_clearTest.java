
package net.hydromatic.morel.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TailList_clearTest {
    private List<String> backingList;
    private TailList<String> tailList;

    @BeforeEach
    public void setUp() {
        backingList = new ArrayList<>(Arrays.asList("a", "b", "c", "d"));
        tailList = new TailList<>(backingList, 2);
    }

    @Test
    public void testClear() {
        tailList.clear();
        assertEquals(0, tailList.size());
        assertEquals(2, backingList.size());
        assertTrue(backingList.containsAll(Arrays.asList("a", "b")));
    }

    @Test
    public void testClearAfterAddAll() {
        tailList.addAll(Arrays.asList("e", "f"));
        tailList.clear();
        assertEquals(0, tailList.size());
        assertEquals(2, backingList.size());
        assertTrue(backingList.containsAll(Arrays.asList("a", "b")));
    }

    @Test
    public void testClearEmptyList() {
        TailList<String> emptyTailList = new TailList<>(new ArrayList<>(), 0);
        emptyTailList.clear();
        assertEquals(0, emptyTailList.size());
        assertTrue(emptyTailList.isEmpty());
    }
}
