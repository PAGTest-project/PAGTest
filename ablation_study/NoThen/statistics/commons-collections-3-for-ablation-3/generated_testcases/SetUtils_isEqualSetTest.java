
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SetUtils_isEqualSetTest {

    @Test
    public void testIsEqualSet_SameReference() {
        Collection<?> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        assertTrue(SetUtils.isEqualSet(set1, set1));
    }

    @Test
    public void testIsEqualSet_NullAndNonNull() {
        Collection<?> set1 = null;
        Collection<?> set2 = new HashSet<>(Arrays.asList(1, 2, 3));
        assertFalse(SetUtils.isEqualSet(set1, set2));
        assertFalse(SetUtils.isEqualSet(set2, set1));
    }

    @Test
    public void testIsEqualSet_DifferentSizes() {
        Collection<?> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Collection<?> set2 = new HashSet<>(Arrays.asList(1, 2));
        assertFalse(SetUtils.isEqualSet(set1, set2));
    }

    @Test
    public void testIsEqualSet_EqualSets() {
        Collection<?> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Collection<?> set2 = new HashSet<>(Arrays.asList(3, 2, 1));
        assertTrue(SetUtils.isEqualSet(set1, set2));
    }

    @Test
    public void testIsEqualSet_DifferentSets() {
        Collection<?> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Collection<?> set2 = new HashSet<>(Arrays.asList(1, 2, 4));
        assertFalse(SetUtils.isEqualSet(set1, set2));
    }
}
