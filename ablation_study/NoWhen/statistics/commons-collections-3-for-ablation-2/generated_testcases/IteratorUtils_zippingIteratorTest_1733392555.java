
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IteratorUtils_zippingIteratorTest {

    @Test
    public void testZippingIterator() {
        // Given
        Iterator<Integer> iterator1 = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> iterator2 = Arrays.asList(4, 5, 6).iterator();
        Iterator<Integer> iterator3 = Arrays.asList(7, 8, 9).iterator();

        // When
        ZippingIterator<Integer> zippingIterator = IteratorUtils.zippingIterator(iterator1, iterator2, iterator3);

        // Then
        assertNotNull(zippingIterator);
    }
}
