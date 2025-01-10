
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUniqueList_clearTest {

    private SetUniqueList<Integer> uniqueList;

    @BeforeEach
    public void setUp() {
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        uniqueList = new SetUniqueList<>(list, set);
    }

    @Test
    public void testClear() {
        uniqueList.add(1);
        uniqueList.add(2);
        uniqueList.add(3);

        uniqueList.clear();

        assertTrue(uniqueList.isEmpty());
        assertTrue(uniqueList.asSet().isEmpty());
    }
}
