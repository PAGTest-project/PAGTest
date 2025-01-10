
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetUtils_hashCodeForSetTest {

    @Test
    public void testHashCodeForSet_NullSet() {
        assertEquals(0, SetUtils.hashCodeForSet(null));
    }

    @Test
    public void testHashCodeForSet_EmptySet() {
        assertEquals(0, SetUtils.hashCodeForSet(Collections.emptySet()));
    }

    @Test
    public void testHashCodeForSet_NonEmptySet() {
        Set<String> set = new HashSet<>(Arrays.asList("a", "b", "c"));
        int expectedHashCode = "a".hashCode() + "b".hashCode() + "c".hashCode();
        assertEquals(expectedHashCode, SetUtils.hashCodeForSet(set));
    }

    @Test
    public void testHashCodeForSet_SetWithNullElements() {
        Set<String> set = new HashSet<>(Arrays.asList("a", null, "c"));
        int expectedHashCode = "a".hashCode() + "c".hashCode();
        assertEquals(expectedHashCode, SetUtils.hashCodeForSet(set));
    }
}
