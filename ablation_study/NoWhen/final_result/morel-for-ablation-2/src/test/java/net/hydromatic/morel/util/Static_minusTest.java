
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_minusTest {

    @Test
    public void testMinus_ElementPresent() {
        List<Integer> list = Arrays.asList(1, 2, 3, 2, 4);
        List<Integer> result = Static.minus(list, 2);
        assertEquals(Arrays.asList(1, 3, 4), result);
    }

    @Test
    public void testMinus_ElementNotPresent() {
        List<Integer> list = Arrays.asList(1, 3, 4);
        List<Integer> result = Static.minus(list, 2);
        assertEquals(Arrays.asList(1, 3, 4), result);
    }

    @Test
    public void testMinus_EmptyList() {
        List<Integer> list = ImmutableList.of();
        List<Integer> result = Static.minus(list, 2);
        assertEquals(ImmutableList.of(), result);
    }
}
