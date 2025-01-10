
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_asIteratorTest {

    @Test
    public void testAsIteratorWithNonNullParameters() {
        Enumeration<String> enumeration = Collections.enumeration(List.of("a", "b", "c"));
        java.util.Collection<String> collection = new ArrayList<>();
        Iterator<String> iterator = IteratorUtils.asIterator(enumeration, collection);
        assertNotNull(iterator);
    }

    @Test
    public void testAsIteratorWithNullEnumeration() {
        java.util.Collection<String> collection = new ArrayList<>();
        assertThrows(NullPointerException.class, () -> IteratorUtils.asIterator(null, collection));
    }

    @Test
    public void testAsIteratorWithNullCollection() {
        Enumeration<String> enumeration = Collections.enumeration(List.of("a", "b", "c"));
        assertThrows(NullPointerException.class, () -> IteratorUtils.asIterator(enumeration, null));
    }
}
