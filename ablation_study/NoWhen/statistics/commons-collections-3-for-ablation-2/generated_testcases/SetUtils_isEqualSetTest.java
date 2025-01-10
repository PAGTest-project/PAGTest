
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SetUtils_isEqualSetTest {

    @Test
    void testIsEqualSet() {
        // Given
        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(3, 2, 1));
        Set<Integer> set3 = new HashSet<>(Arrays.asList(1, 2));
        Set<Integer> set4 = null;

        // When and Then
        assertTrue(SetUtils.isEqualSet(set1, set2)); // set1 and set2 are equal
        assertFalse(SetUtils.isEqualSet(set1, set3)); // set1 and set3 are not equal
        assertFalse(SetUtils.isEqualSet(set1, set4)); // set1 is not null, set4 is null
        assertFalse(SetUtils.isEqualSet(set4, set1)); // set4 is null, set1 is not null
        assertTrue(SetUtils.isEqualSet(set4, set4)); // both sets are null
    }
}
