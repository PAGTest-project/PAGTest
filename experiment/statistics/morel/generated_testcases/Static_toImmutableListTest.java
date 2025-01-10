
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Static_toImmutableListTest {

    @Test
    public void testToImmutableList() {
        List<String> inputList = Arrays.asList("a", "b", "c");
        Stream<String> inputStream = inputList.stream();

        ImmutableList<String> result = inputStream.collect(Static.toImmutableList());

        assertEquals(inputList, result);
    }
}
