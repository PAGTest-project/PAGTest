
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
        String value = "test";
        Equator<String> equator = mock(Equator.class);
        when(equator.equate(value, value)).thenReturn(true);
        Predicate<String> predicate = EqualPredicate.equalPredicate(value, equator);

        // When
        boolean result = predicate.test(value);

        // Then
        assertTrue(result);
        verify(equator).equate(value, value);
    }

    @Test
    public void testWithoutEquator() {
        // Given
        String value = "test";
        Predicate<String> predicate = EqualPredicate.equalPredicate(value);

        // When
        boolean result = predicate.test(value);

        // Then
        assertTrue(result);
    }
}
