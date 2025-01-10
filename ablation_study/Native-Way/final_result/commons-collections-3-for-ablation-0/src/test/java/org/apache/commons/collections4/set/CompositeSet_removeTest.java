
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

public class CompositeSet_removeTest {

    @Test
    public void testRemove_ElementPresentInFirstSet() {
        CompositeSet<Integer> compositeSet = new CompositeSet<>();
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        compositeSet.addComposited(set1);

        assertTrue(compositeSet.remove(1));
        assertFalse(set1.contains(1));
    }

    @Test
    public void testRemove_ElementNotPresent() {
        CompositeSet<Integer> compositeSet = new CompositeSet<>();
        Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);
        compositeSet.addComposited(set1);

        assertFalse(compositeSet.remove(3));
    }
}
