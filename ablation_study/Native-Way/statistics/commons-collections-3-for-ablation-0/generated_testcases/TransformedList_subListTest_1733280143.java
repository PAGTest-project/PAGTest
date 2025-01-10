
package org.apache.commons.collections4.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections4.Transformer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransformedList_subListTest {

    private TransformedList<String> transformedList;
    private Transformer<String, String> transformer;

    @BeforeEach
    public void setUp() {
        List<String> originalList = new ArrayList<>();
        originalList.add("1");
        originalList.add("2");
        originalList.add("3");
        transformer = new Transformer<String, String>() {
            @Override
            public String transform(String input) {
                return input + " transformed";
            }
        };
        transformedList = new TransformedList<>(originalList, transformer);
    }

    @Test
    public void testSubList_ValidRange() {
        List<String> subList = transformedList.subList(1, 3);
        assertEquals(2, subList.size());
        assertTrue(subList.contains("2 transformed"));
        assertTrue(subList.contains("3 transformed"));
    }

    @Test
    public void testSubList_SingleElement() {
        List<String> subList = transformedList.subList(0, 1);
        assertEquals(1, subList.size());
        assertTrue(subList.contains("1 transformed"));
    }

    @Test
    public void testSubList_EmptySubList() {
        List<String> subList = transformedList.subList(1, 1);
        assertTrue(subList.isEmpty());
    }
}
