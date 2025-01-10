
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Set;
import java.util.HashSet;
import static org.junit.jupiter.api.Assertions.*;

public class SetUtils_disjunctionTest {

    @Test
    public void testDisjunction() {
        Set<Integer> setA = new HashSet<>(Set.of(1, 2, 3));
        Set<Integer> setB = new HashSet<>(Set.of(3, 4, 5));

        SetView<Integer> result = SetUtils.disjunction(setA, setB);

        assertEquals(4, result.size());
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(4));
        assertTrue(result.contains(5));
    }
}
