
package org.apache.commons.collections4.iterators;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FilterListIterator_nextTest {

    private ArrayList<Integer> list;
    private Predicate<Integer> threePred;
    private FilterListIterator<Integer> filterListIterator;

    @BeforeEach
    public void setUp() {
        list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add(Integer.valueOf(i));
        }
        threePred = x -> x % 3 == 0;
        filterListIterator = new FilterListIterator<>(list.listIterator(), threePred);
    }

    @Test
    public void testNextWithValidElement() {
        filterListIterator.setListIterator(list.listIterator());
        filterListIterator.setPredicate(threePred);
        assertEquals(Integer.valueOf(0), filterListIterator.next());
        assertEquals(Integer.valueOf(3), filterListIterator.next());
        assertEquals(Integer.valueOf(6), filterListIterator.next());
    }

    @Test
    public void testNextWithNoValidElement() {
        filterListIterator.setListIterator(list.listIterator());
        filterListIterator.setPredicate(x -> false);
        assertThrows(NoSuchElementException.class, () -> filterListIterator.next());
    }
}
