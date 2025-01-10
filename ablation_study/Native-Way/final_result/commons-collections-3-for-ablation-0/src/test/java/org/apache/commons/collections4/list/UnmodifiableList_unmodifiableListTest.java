
package org.apache.commons.collections4.list;

import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableList_unmodifiableListTest {

    @Test
    public void testUnmodifiableListWithUnmodifiableList() {
        List<String> originalList = new ArrayList<>();
        originalList.add("test");
        List<String> unmodifiableList = new UnmodifiableList<>(originalList);

        List<String> result = UnmodifiableList.unmodifiableList(unmodifiableList);

        assertSame(unmodifiableList, result);
    }

    @Test
    public void testUnmodifiableListWithModifiableList() {
        List<String> originalList = new ArrayList<>();
        originalList.add("test");

        List<String> result = UnmodifiableList.unmodifiableList(originalList);

        assertTrue(result instanceof UnmodifiableList);
    }
}
