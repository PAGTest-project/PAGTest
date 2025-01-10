
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IteratorUtils_zippingIteratorTest {

    @Test
    public void testZippingIterator() {
        // Given
        List<Integer> listA = Arrays.asList(1, 2, 3);
        List<Integer> listB = Arrays.asList(4, 5, 6);
        List<Integer> listC = Arrays.asList(7, 8, 9);

        Iterator<Integer> iteratorA = listA.iterator();
        Iterator<Integer> iteratorB = listB.iterator();
        Iterator<Integer> iteratorC = listC.iterator();

        // When
        ZippingIterator<Integer> zippingIterator = IteratorUtils.zippingIterator(iteratorA, iteratorB, iteratorC);

        // Then
        List<Integer> expectedZippedList = Arrays.asList(1, 4, 7, 2, 5, 8, 3, 6, 9);
        for (Integer expected : expectedZippedList) {
            assertEquals(expected, zippingIterator.next());
        }
    }
}
