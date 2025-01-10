
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.Transformer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CollectionUtils_transformingCollectionTest {

    @Test
    public void testTransformingCollection_Success() {
        Collection<String> input = Arrays.asList("a", "b", "c");
        Transformer<String, String> transformer = s -> s.toUpperCase();

        Collection<String> result = CollectionUtils.transformingCollection(input, transformer);

        assertEquals(Arrays.asList("A", "B", "C"), result);
    }

    @Test
    public void testTransformingCollection_NullCollection() {
        Transformer<String, String> transformer = s -> s.toUpperCase();

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.transformingCollection(null, transformer);
        });
    }

    @Test
    public void testTransformingCollection_NullTransformer() {
        Collection<String> input = Arrays.asList("a", "b", "c");

        assertThrows(NullPointerException.class, () -> {
            CollectionUtils.transformingCollection(input, null);
        });
    }
}
