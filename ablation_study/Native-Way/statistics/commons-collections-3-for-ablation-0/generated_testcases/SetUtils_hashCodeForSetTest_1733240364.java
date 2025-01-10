
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUtils_hashCodeForSetTest {
    private Set<Integer> setA;
    private Set<Integer> setB;

    @BeforeEach
    public void setUp() {
        setA = new HashSet<>();
        setA.add(1);
        setA.add(2);
        setA.add(3);
        setA.add(4);
        setA.add(5);

        setB = new HashSet<>();
        setB.add(3);
        setB.add(4);
        setB.add(5);
        setB.add(6);
        setB.add(7);
    }

    @Test
    public void testHashCodeForSetWithNonNullElements() {
        int expectedHashCode = 1.hashCode() + 2.hashCode() + 3.hashCode() + 4.hashCode() + 5.hashCode();
        assertEquals(expectedHashCode, SetUtils.hashCodeForSet(setA));
    }

    @Test
    public void testHashCodeForSetWithNullElements() {
        setA.add(null);
        int expectedHashCode = 1.hashCode() + 2.hashCode() + 3.hashCode() + 4.hashCode() + 5.hashCode();
        assertEquals(expectedHashCode, SetUtils.hashCodeForSet(setA));
    }

    @Test
    public void testHashCodeForSetWithAllNullElements() {
        Set<Integer> nullSet = new HashSet<>();
        nullSet.add(null);
        nullSet.add(null);
        assertEquals(0, SetUtils.hashCodeForSet(nullSet));
    }

    @Test
    public void testHashCodeForSetWithEmptySet() {
        Set<Integer> emptySet = new HashSet<>();
        assertEquals(0, SetUtils.hashCodeForSet(emptySet));
    }

    @Test
    public void testHashCodeForSetWithNullSet() {
        assertEquals(0, SetUtils.hashCodeForSet(null));
    }

    @Test
    public void testHashCodeForSetWithDifferentSets() {
        int hashCodeA = SetUtils.hashCodeForSet(setA);
        int hashCodeB = SetUtils.hashCodeForSet(setB);
        assertNotEquals(hashCodeA, hashCodeB);
    }

    @Test
    public void testHashCodeForSetWithSameSets() {
        Set<Integer> setC = new HashSet<>(setA);
        assertEquals(SetUtils.hashCodeForSet(setA), SetUtils.hashCodeForSet(setC));
    }

    @Test
    public void testHashCodeForSetWithLargeSet() {
        Set<Integer> largeSet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            largeSet.add(i);
        }
        int expectedHashCode = 0;
        for (int i = 0; i < 1000; i++) {
            expectedHashCode += i;
        }
        assertEquals(expectedHashCode, SetUtils.hashCodeForSet(largeSet));
    }
}
