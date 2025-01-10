
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Collections;

public class CollectionUtils_getTest {

    @Test
    public void testGetWithValidIndex() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        assertEquals("b", IterableUtils.get(iterable, 1));
    }

    @Test
    public void testGetWithNullIterable() {
        assertThrows(NullPointerException.class, () -> {
            IterableUtils.get(null, 0);
        });
    }
}
