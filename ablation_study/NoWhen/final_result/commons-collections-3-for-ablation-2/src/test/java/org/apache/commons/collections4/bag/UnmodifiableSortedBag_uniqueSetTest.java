
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableSortedBag_uniqueSetTest {

    @Test
    public void testUniqueSet() {
        // Given
        SortedBag<String> bag = new TreeBag<>();
        bag.add("apple");
        bag.add("banana");
        bag.add("apple");
        SortedBag<String> unmodifiableBag = UnmodifiableSortedBag.unmodifiableSortedBag(bag);

        // When
        Set<String> uniqueSet = unmodifiableBag.uniqueSet();

        // Then
        assertTrue(uniqueSet instanceof UnmodifiableSet);
        assertTrue(uniqueSet.contains("apple"));
        assertTrue(uniqueSet.contains("banana"));
    }
}
