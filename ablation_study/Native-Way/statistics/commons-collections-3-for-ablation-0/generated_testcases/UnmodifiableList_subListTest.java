
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnmodifiableList_subListTest {

    private List<String> originalList;
    private UnmodifiableList<String> unmodifiableList;

    @BeforeEach
    public void setupList() {
        originalList = new ArrayList<>(Arrays.asList("A", "B", "C"));
        unmodifiableList = new UnmodifiableList<>(originalList);
    }

    @Test
    public void testSubList() {
        List<String> subList = unmodifiableList.subList(1, 3);
        assertAll("subList properties",
            () -> assertEquals(2, subList.size()),
            () -> assertTrue(subList.contains("B")),
            () -> assertTrue(subList.contains("C"))
        );
    }

    @Test
    public void testSubListUnmodifiable() {
        List<String> subList = unmodifiableList.subList(1, 3);
        assertThrows(UnsupportedOperationException.class, () -> subList.add("D"),
            "Expecting UnsupportedOperationException.");
    }

    @Test
    public void testSubListOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> unmodifiableList.subList(1, 4),
            "Expecting IndexOutOfBoundsException.");
    }
}
