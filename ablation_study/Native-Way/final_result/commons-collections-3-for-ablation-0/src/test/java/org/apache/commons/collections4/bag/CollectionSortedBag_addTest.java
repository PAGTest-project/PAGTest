
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class CollectionSortedBag_addTest {

    @Test
    public void testAdd() {
        SortedBag<String> mockBag = mock(SortedBag.class);
        CollectionSortedBag<String> bag = new CollectionSortedBag<>(mockBag);

        bag.add("element", 2);

        verify(mockBag).add("element", 2);
    }
}
