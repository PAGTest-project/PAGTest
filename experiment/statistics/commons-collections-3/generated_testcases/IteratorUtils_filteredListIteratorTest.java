
package org.apache.commons.collections4;

import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.iterators.FilterListIterator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IteratorUtils_filteredListIteratorTest {

    @Test
    public void testFilteredListIterator_Success() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        ListIterator<String> listIterator = list.listIterator();
        Predicate<String> predicate = TruePredicate.truePredicate();

        ListIterator<String> filteredIterator = IteratorUtils.filteredListIterator(listIterator, new org.apache.commons.collections4.Predicate<String>() {
            @Override
            public boolean evaluate(String object) {
                return predicate.test(object);
            }
        });
        assertNotNull(filteredIterator);
    }

    @Test
    public void testFilteredListIterator_NullListIterator() {
        Predicate<String> predicate = TruePredicate.truePredicate();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredListIterator(null, new org.apache.commons.collections4.Predicate<String>() {
                @Override
                public boolean evaluate(String object) {
                    return predicate.test(object);
                }
            });
        });
    }

    @Test
    public void testFilteredListIterator_NullPredicate() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        ListIterator<String> listIterator = list.listIterator();

        assertThrows(NullPointerException.class, () -> {
            IteratorUtils.filteredListIterator(listIterator, (org.apache.commons.collections4.Predicate<String>) null);
        });
    }
}
