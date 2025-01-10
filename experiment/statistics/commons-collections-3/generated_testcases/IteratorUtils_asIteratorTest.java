
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_asIteratorTest {

    @Test
    public void testAsIterator_NonNullParameters() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Enumeration<String> enumeration = Collections.enumeration(list);
        List<String> removeCollection = new ArrayList<>();

        Iterator<String> iterator = IteratorUtils.asIterator(enumeration, removeCollection);
        assertNotNull(iterator);
    }

    @Test
    public void testAsIterator_NullEnumeration() {
        List<String> removeCollection = new ArrayList<>();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.asIterator(null, removeCollection);
        });
    }

    @Test
    public void testAsIterator_NullRemoveCollection() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        Enumeration<String> enumeration = Collections.enumeration(list);

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.asIterator(enumeration, null);
        });
    }
}
