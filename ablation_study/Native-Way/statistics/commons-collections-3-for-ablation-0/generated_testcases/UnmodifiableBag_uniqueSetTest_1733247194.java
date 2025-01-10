
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableBag_uniqueSetTest {

    private UnmodifiableBag<String> unmodifiableBag;
    private Bag<String> decoratedBag;

    @BeforeEach
    public void setUp() {
        decoratedBag = new HashBag<>(Set.of("A", "B", "C"));
        unmodifiableBag = new UnmodifiableBag<>(decoratedBag);
    }

    @Test
    public void testUniqueSet() {
        Set<String> uniqueSet = unmodifiableBag.uniqueSet();
        assertTrue(uniqueSet instanceof UnmodifiableSet);
        assertEquals(decoratedBag.uniqueSet(), uniqueSet);
    }

    @Test
    public void testUniqueSetUnmodifiable() {
        Set<String> uniqueSet = unmodifiableBag.uniqueSet();
        assertThrows(UnsupportedOperationException.class, () -> uniqueSet.add("D"));
    }
}
