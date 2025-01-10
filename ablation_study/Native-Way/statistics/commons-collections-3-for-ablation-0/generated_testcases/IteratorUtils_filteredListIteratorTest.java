
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.iterators.FilterListIterator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_filteredListIteratorTest {

    @Test
    public void testFilteredListIterator_Success() {
        List<String> list = Arrays.asList("a", "b", "c");
        ListIterator<String> listIterator = list.listIterator();
        FilterListIterator<String> filteredIterator = (FilterListIterator<String>) IteratorUtils.filteredListIterator(listIterator, TruePredicate.truePredicate());
        assertNotNull(filteredIterator);
    }

    @Test
    public void testFilteredListIterator_NullListIterator() {
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredListIterator(null, TruePredicate.truePredicate());
        });
    }

    @Test
    public void testFilteredListIterator_NullPredicate() {
        List<String> list = Arrays.asList("a", "b", "c");
        ListIterator<String> listIterator = list.listIterator();
        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredListIterator(listIterator, null);
        });
    }
}
