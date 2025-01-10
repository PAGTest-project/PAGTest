
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ListUtils_removeAllTest {

    @Test
    public void testRemoveAll_NormalCase() {
        Collection<String> collection = Arrays.asList("a", "b", "c", "d");
        Collection<String> remove = Arrays.asList("b", "d");

        List<String> result = ListUtils.removeAll(collection, remove);

        assertEquals(Arrays.asList("a", "c"), result);
    }

    @Test
    public void testRemoveAll_NullCollection() {
        Collection<String> remove = Arrays.asList("b", "d");

        assertThrows(NullPointerException.class, () -> {
            ListUtils.removeAll(null, remove);
        });
    }

    @Test
    public void testRemoveAll_NullRemove() {
        Collection<String> collection = Arrays.asList("a", "b", "c", "d");

        assertThrows(NullPointerException.class, () -> {
            ListUtils.removeAll(collection, null);
        });
    }
}
