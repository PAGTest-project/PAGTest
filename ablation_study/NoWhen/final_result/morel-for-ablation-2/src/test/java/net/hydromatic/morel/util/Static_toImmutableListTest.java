
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_toImmutableListTest {

    @Test
    public void testToImmutableList() {
        Stream<Integer> stream = Stream.of(1, 2, 3);
        ImmutableList<Integer> result = stream.collect(Static.toImmutableList());
        assertEquals(ImmutableList.of(1, 2, 3), result);
    }
}
