
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Static_toImmutableListTest {

    @Test
    void testToImmutableList() {
        List<String> inputList = Arrays.asList("a", "b", "c");
        Stream<String> stream = inputList.stream();

        ImmutableList<String> result = stream.collect(Static.toImmutableList());

        assertEquals(inputList, result);
    }
}
