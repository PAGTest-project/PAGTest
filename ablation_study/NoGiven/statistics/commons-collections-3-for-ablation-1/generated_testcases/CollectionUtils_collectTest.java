
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtils_collectTest {

    @Test
    public void testCollectWithNonNullInputCollection() {
        List<String> inputCollection = Arrays.asList("a", "b", "c");
        Transformer<String, String> transformer = s -> s.toUpperCase();
        List<String> outputCollection = new ArrayList<>();

        Collection<String> result = CollectionUtils.collect((Iterable<String>) inputCollection, transformer, outputCollection);

        assertEquals(Arrays.asList("A", "B", "C"), result);
    }

    @Test
    public void testCollectWithNullInputCollection() {
        Transformer<String, String> transformer = s -> s.toUpperCase();
        List<String> outputCollection = new ArrayList<>();

        Collection<String> result = CollectionUtils.collect((Iterable<String>) null, transformer, outputCollection);

        assertEquals(Collections.emptyList(), result);
    }
}
