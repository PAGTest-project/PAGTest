
package org.apache.commons.collections4;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.collections4.SetUtils.SetView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SetUtils_unionTest {
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
    public void testUnion() {
        final SetView<Integer> set = SetUtils.union(setA, setB);
        assertEquals(7, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertTrue(set.contains(5));
        assertTrue(set.contains(6));
        assertTrue(set.contains(7));

        final SetView<Integer> emptyUnion = SetUtils.union(setA, SetUtils.<Integer>emptySet());
        assertEquals(setA.size(), emptyUnion.size());
        assertTrue(emptyUnion.containsAll(setA));

        assertAll(
                () -> assertThrows(NullPointerException.class, () -> SetUtils.union(setA, null),
                        "Expecting NullPointerException"),
                () -> assertThrows(NullPointerException.class, () -> SetUtils.union(null, setB),
                        "Expecting NullPointerException")
        );
    }
}
