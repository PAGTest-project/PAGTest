
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.set.UnmodifiableSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class UnmodifiableBag_uniqueSetTest {

    private UnmodifiableBag<String> unmodifiableBag;
    private Bag<String> decoratedBag;

    @BeforeEach
    public void setUp() {
        Set<String> elements = new HashSet<>();
        elements.add("A");
        elements.add("B");
        elements.add("C");
        decoratedBag = new HashBag<>(elements);
        unmodifiableBag = UnmodifiableBag.unmodifiableBag(decoratedBag);
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
