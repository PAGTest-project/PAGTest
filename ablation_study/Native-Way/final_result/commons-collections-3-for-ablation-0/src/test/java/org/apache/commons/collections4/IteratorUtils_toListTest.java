
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IteratorUtils_toListTest {

    @Test
    void testToList_SuccessfulConversion() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        List<String> result = IteratorUtils.toList(iterator, 3);
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    void testToList_NullIterator() {
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.toList(null, 3);
        });
    }

    @Test
    void testToList_InvalidEstimatedSize() {
        Iterator<String> iterator = Arrays.asList("a", "b", "c").iterator();
        assertThrows(IllegalArgumentException.class, () -> {
            IteratorUtils.toList(iterator, 0);
        });
    }
}
