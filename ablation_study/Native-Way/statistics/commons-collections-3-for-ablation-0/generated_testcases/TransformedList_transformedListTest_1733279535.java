
package org.apache.commons.collections4.list;

import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformedList_transformedListTest {

    @Test
    public void testTransformedListWithNonEmptyList() {
        List<Integer> originalList = Arrays.asList(1, 2, 3);
        Transformer<Integer, Integer> transformer = i -> i * 2;

        TransformedList<Integer> transformedList = TransformedList.transformedList(originalList, transformer);

        assertEquals(0, originalList.size());
        assertEquals(3, transformedList.size());
        assertEquals(Arrays.asList(2, 4, 6), transformedList);
    }

    @Test
    public void testTransformedListWithEmptyList() {
        List<Integer> originalList = Arrays.asList();
        Transformer<Integer, Integer> transformer = i -> i * 2;

        TransformedList<Integer> transformedList = TransformedList.transformedList(originalList, transformer);

        assertTrue(originalList.isEmpty());
        assertTrue(transformedList.isEmpty());
    }
}
