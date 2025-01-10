
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnmodifiableBag_unmodifiableBagTest {

    @Test
    void testUnmodifiableBagWithUnmodifiableBag() {
        // Given
        Bag<String> originalBag = new UnmodifiableBag<>(new HashBag<>());

        // When
        Bag<String> resultBag = UnmodifiableBag.unmodifiableBag(originalBag);

        // Then
        assertSame(originalBag, resultBag);
    }

    @Test
    void testUnmodifiableBagWithModifiableBag() {
        // Given
        Bag<String> originalBag = new HashBag<>();

        // When
        Bag<String> resultBag = UnmodifiableBag.unmodifiableBag(originalBag);

        // Then
        assertTrue(resultBag instanceof Unmodifiable);
    }
}
