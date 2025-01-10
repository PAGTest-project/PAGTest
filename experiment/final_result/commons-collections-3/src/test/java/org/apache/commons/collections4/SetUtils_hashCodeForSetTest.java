
package org.apache.commons.collections4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetUtils_hashCodeForSetTest {

    private Set<Integer> setA;

    @BeforeEach
    public void setUp() {
        setA = new HashSet<>();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        setA.add(5);
    }

    @Test
    public void testHashCodeForSet() {
        // Given a non-null set
        Set<Integer> set = new HashSet<>(setA);

        // When calculating the hash code
        int hashCode = SetUtils.hashCodeForSet(set);

        // Then the hash code should be the sum of the hash codes of its elements
        int expectedHashCode = 1 + 2 + 3 + 4 + 5;
        assertEquals(expectedHashCode, hashCode);
    }

    @Test
    public void testHashCodeForSetWithNull() {
        // Given a null set
        Set<Integer> set = null;

        // When calculating the hash code
        int hashCode = SetUtils.hashCodeForSet(set);

        // Then the hash code should be 0
        assertEquals(0, hashCode);
    }

    @Test
    public void testHashCodeForSetWithNullElements() {
        // Given a set with null elements
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(null);
        set.add(3);

        // When calculating the hash code
        int hashCode = SetUtils.hashCodeForSet(set);

        // Then the hash code should be the sum of the hash codes of non-null elements
        int expectedHashCode = 1 + 3;
        assertEquals(expectedHashCode, hashCode);
    }
}
