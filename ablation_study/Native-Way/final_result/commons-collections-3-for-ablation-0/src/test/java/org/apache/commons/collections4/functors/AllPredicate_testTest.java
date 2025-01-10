
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AllPredicate_testTest {

    @Test
    public void testAllPredicatesTrue() {
        Predicate<String> predicate1 = mock(Predicate.class);
        Predicate<String> predicate2 = mock(Predicate.class);

        when(predicate1.test("testObject")).thenReturn(true);
        when(predicate2.test("testObject")).thenReturn(true);

        AllPredicate<String> allPredicate = new AllPredicate<>(predicate1, predicate2);
        assertTrue(allPredicate.test("testObject"));
    }

    @Test
    public void testOnePredicateFalse() {
        Predicate<String> predicate1 = mock(Predicate.class);
        Predicate<String> predicate2 = mock(Predicate.class);

        when(predicate1.test("testObject")).thenReturn(true);
        when(predicate2.test("testObject")).thenReturn(false);

        AllPredicate<String> allPredicate = new AllPredicate<>(predicate1, predicate2);
        assertFalse(allPredicate.test("testObject"));
    }
}
