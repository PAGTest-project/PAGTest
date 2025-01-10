
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_asIteratorTest {

    @Test
    public void testAsIterator_NonNullParameters() {
        Enumeration<String> enumeration = Collections.enumeration(List.of("a", "b", "c"));
        List<String> removeCollection = List.of();

        Iterator<String> iterator = IteratorUtils.asIterator(enumeration, removeCollection);
        assertNotNull(iterator);
    }

    @Test
    public void testAsIterator_NullEnumeration() {
        List<String> removeCollection = List.of();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.asIterator(null, removeCollection);
        });
    }

    @Test
    public void testAsIterator_NullRemoveCollection() {
        Enumeration<String> enumeration = Collections.enumeration(List.of("a", "b", "c"));

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.asIterator(enumeration, null);
        });
    }
}
