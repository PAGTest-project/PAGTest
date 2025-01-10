
package org.apache.commons.collections4.functors;

import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.Predicate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EqualPredicate_testTest {

    @Test
    public void testWithEquator() {
        // Given
        String expectedValue = "test";
        Equator<String> equator = mock(Equator.class);
        Predicate<String> predicate = EqualPredicate.equalPredicate(expectedValue, equator);

        // When
        when(equator.equate(expectedValue, expectedValue)).thenReturn(true);
        boolean result = predicate.test(expectedValue);

        // Then
        assertTrue(result);
        verify(equator).equate(expectedValue, expectedValue);
    }

    @Test
    public void testWithoutEquator() {
        // Given
        String expectedValue = "test";
        Predicate<String> predicate = EqualPredicate.equalPredicate(expectedValue);

        // When
        boolean result = predicate.test(expectedValue);

        // Then
        assertTrue(result);
    }
}
