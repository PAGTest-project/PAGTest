
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CollectionUtils_transformTest {

    @Test
    public void testTransformList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        Transformer<Integer, Integer> transformer = i -> i * 2;

        CollectionUtils.transform(list, transformer);

        assertEquals(Arrays.asList(2, 4, 6), list);
    }

    @Test
    public void testTransformCollection() {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3));
        Transformer<Integer, Integer> transformer = i -> i * 2;

        CollectionUtils.transform(collection, transformer);

        assertEquals(Arrays.asList(2, 4, 6), collection);
    }

    @Test
    public void testTransformNullCollection() {
        Collection<Integer> collection = null;
        Transformer<Integer, Integer> transformer = i -> i * 2;

        CollectionUtils.transform(collection, transformer);

        // No exception should be thrown
    }

    @Test
    public void testTransformNullTransformer() {
        Collection<Integer> collection = new ArrayList<>(Arrays.asList(1, 2, 3));
        Transformer<Integer, Integer> transformer = null;

        CollectionUtils.transform(collection, transformer);

        // No exception should be thrown
    }
}
