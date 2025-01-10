
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListUtils_retainAllTest {

    @Test
    public void testRetainAll() {
        Collection<Integer> collection = Arrays.asList(1, 2, 3, 4, 5);
        Collection<Integer> retain = Arrays.asList(4, 5, 6, 7);

        List<Integer> result = ListUtils.retainAll(collection, retain);

        assertEquals(Arrays.asList(4, 5), result);
    }
}
