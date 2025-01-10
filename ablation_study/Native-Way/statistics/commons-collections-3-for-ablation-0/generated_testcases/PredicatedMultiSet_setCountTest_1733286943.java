
package org.apache.commons.collections4.multiset;

import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PredicatedMultiSet_setCountTest {

    private PredicatedMultiSet<String> predicatedMultiSet;
    private MultiSet<String> decoratedMultiSet;
    private Predicate<String> predicate;

    @BeforeEach
    public void setUp() {
        decoratedMultiSet = mock(MultiSet.class);
        predicate = mock(Predicate.class);
        predicatedMultiSet = new PredicatedMultiSet<>(decoratedMultiSet, predicate);
    }

    @Test
    public void testSetCount_ValidObject() {
        String validObject = "valid";
        int count = 5;

        when(predicate.evaluate(validObject)).thenReturn(true);
        when(decoratedMultiSet.setCount(validObject, count)).thenReturn(3);

        int result = predicatedMultiSet.setCount(validObject, count);

        assertEquals(3, result);
        verify(predicate).evaluate(validObject);
        verify(decoratedMultiSet).setCount(validObject, count);
    }
}
