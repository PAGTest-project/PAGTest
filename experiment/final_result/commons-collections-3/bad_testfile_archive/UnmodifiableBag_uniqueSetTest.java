
package org.apache.commons.collections4.bag;

import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.bag.UnmodifiableBag;
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
        decoratedBag = new HashBag<>();
        decoratedBag.add("element1");
        decoratedBag.add("element2");
        unmodifiableBag = UnmodifiableBag.unmodifiableBag(decoratedBag);
    }

    @Test
    public void testUniqueSet() {
        Set<String> uniqueSet = unmodifiableBag.uniqueSet();
        assertTrue(uniqueSet instanceof UnmodifiableSet);
        assertEquals(2, uniqueSet.size());
        assertTrue(uniqueSet.contains("element1"));
        assertTrue(uniqueSet.contains("element2"));
    }
}
