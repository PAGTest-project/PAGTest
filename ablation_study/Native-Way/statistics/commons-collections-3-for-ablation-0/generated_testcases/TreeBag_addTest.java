
package org.apache.commons.collections4.bag;

import org.junit.jupiter.api.Test;
import java.util.Comparator;
import static org.junit.jupiter.api.Assertions.*;

class TreeBag_addTest {

    @Test
    void testAddWithComparableObject() {
        TreeBag<String> bag = new TreeBag<>();
        assertTrue(bag.add("test"));
    }

    @Test
    void testAddWithNonComparableObjectAndNoComparator() {
        TreeBag<Object> bag = new TreeBag<>();
        assertThrows(IllegalArgumentException.class, () -> bag.add(new Object()));
    }

    @Test
    void testAddWithNonComparableObjectAndCustomComparator() {
        TreeBag<Object> bag = new TreeBag<>(Comparator.comparingInt(Object::hashCode));
        assertTrue(bag.add(new Object()));
    }
}
