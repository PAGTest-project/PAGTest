
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.Unmodifiable;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class UnmodifiableBag_unmodifiableBagTest {

    @Test
    public void testUnmodifiableBagWithUnmodifiableBag() {
        // Given
        Bag<String> originalBag = new UnmodifiableBag<>(new HashBag<>());

        // When
        Bag<String> resultBag = UnmodifiableBag.unmodifiableBag(originalBag);

        // Then
        assertSame(originalBag, resultBag);
        assertTrue(resultBag instanceof Unmodifiable);
    }

    @Test
    public void testUnmodifiableBagWithModifiableBag() {
        // Given
        Bag<String> originalBag = new HashBag<>();

        // When
        Bag<String> resultBag = UnmodifiableBag.unmodifiableBag(originalBag);

        // Then
        assertInstanceOf(UnmodifiableBag.class, resultBag);
        assertTrue(resultBag instanceof Unmodifiable);
    }
}
