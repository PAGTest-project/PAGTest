
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UnmodifiableBag_uniqueSetTest {

    @Test
    public void testUniqueSet() {
        // Given
        Bag<String> bag = new HashBag<>();
        bag.add("apple");
        bag.add("banana");
        bag.add("apple");

        UnmodifiableBag<String> unmodifiableBag = new UnmodifiableBag<>(bag);

        // When
        Set<String> uniqueSet = unmodifiableBag.uniqueSet();

        // Then
        assertTrue(uniqueSet instanceof UnmodifiableSet);
        assertTrue(uniqueSet.contains("apple"));
        assertTrue(uniqueSet.contains("banana"));
    }
}
