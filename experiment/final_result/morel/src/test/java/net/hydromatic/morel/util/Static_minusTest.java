
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_minusTest {

    @Test
    public void testMinus_ElementPresent() {
        List<String> list = Arrays.asList("a", "b", "c", "b");
        List<String> result = Static.minus(list, "b");
        assertEquals(Arrays.asList("a", "c"), result);
    }

    @Test
    public void testMinus_ElementNotPresent() {
        List<String> list = Arrays.asList("a", "b", "c");
        List<String> result = Static.minus(list, "d");
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }
}
