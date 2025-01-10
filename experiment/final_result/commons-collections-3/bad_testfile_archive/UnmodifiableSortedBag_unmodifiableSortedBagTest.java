
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.SortedBag;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class UnmodifiableSortedBag_unmodifiableSortedBagTest {

    @Test
    void testUnmodifiableSortedBag_WithUnmodifiableBag() {
        // Given
        SortedBag<String> unmodifiableBag = new UnmodifiableSortedBag<>(new TreeBag<>());

        // When
        SortedBag<String> result = UnmodifiableSortedBag.unmodifiableSortedBag(unmodifiableBag);

        // Then
        assertSame(unmodifiableBag, result);
    }

    @Test
    void testUnmodifiableSortedBag_WithModifiableBag() {
        // Given
        SortedBag<String> modifiableBag = new TreeBag<>();

        // When
        SortedBag<String> result = UnmodifiableSortedBag.unmodifiableSortedBag(modifiableBag);

        // Then
        assertNotSame(modifiableBag, result);
        assertSame(UnmodifiableSortedBag.class, result.getClass());
    }
}
