
package org.apache.commons.collections4.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompositeSet_clearTest {

    private CompositeSet<String> compositeSet;
    private Set<String> set1;
    private Set<String> set2;

    @BeforeEach
    public void setUp() {
        compositeSet = new CompositeSet<>();
        set1 = new HashSet<>();
        set2 = new HashSet<>();
        set1.add("element1");
        set2.add("element2");
        compositeSet.addComposited(set1, set2);
    }

    @Test
    public void testClear() {
        compositeSet.clear();
        assertTrue(set1.isEmpty());
        assertTrue(set2.isEmpty());
        assertEquals(0, compositeSet.size());
    }
}
