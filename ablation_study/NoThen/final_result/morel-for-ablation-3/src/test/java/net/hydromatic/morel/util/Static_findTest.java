
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_findTest {

    @Test
    public void testFindRandomAccessList() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i == 3;
        int result = Static.find(list, predicate);
        assertEquals(2, result);
    }

    @Test
    public void testFindNonRandomAccessList() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i == 3;
        int result = Static.find(list, predicate);
        assertEquals(2, result);
    }

    @Test
    public void testFindNoMatch() {
        List<Integer> list = ImmutableList.of(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = i -> i == 6;
        int result = Static.find(list, predicate);
        assertEquals(-1, result);
    }
}
