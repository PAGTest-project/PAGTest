
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.type.RangeExtent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Extents_isInfiniteTest {

    @Test
    void testIsInfinite_InfiniteRange() {
        // Given
        Core.Exp exp = mock(Core.Exp.class);
        Core.Apply apply = mock(Core.Apply.class);
        Core.Literal literal = mock(Core.Literal.class);
        RangeExtent rangeExtent = mock(RangeExtent.class);

        when(exp.isCallTo(BuiltIn.Z_EXTENT)).thenReturn(true);
        when(exp).thenReturn(apply);
        when(apply.arg).thenReturn(literal);
        when(literal.unwrap(RangeExtent.class)).thenReturn(rangeExtent);
        when(rangeExtent.iterable).thenReturn(null);

        // When
        boolean result = Extents.isInfinite(exp);

        // Then
        assertTrue(result);
    }

    @Test
    void testIsInfinite_FiniteRange() {
        // Given
        Core.Exp exp = mock(Core.Exp.class);
        Core.Apply apply = mock(Core.Apply.class);
        Core.Literal literal = mock(Core.Literal.class);
        RangeExtent rangeExtent = mock(RangeExtent.class);
        Iterable<?> iterable = mock(Iterable.class);

        when(exp.isCallTo(BuiltIn.Z_EXTENT)).thenReturn(true);
        when(exp).thenReturn(apply);
        when(apply.arg).thenReturn(literal);
        when(literal.unwrap(RangeExtent.class)).thenReturn(rangeExtent);
        when(rangeExtent.iterable).thenReturn(iterable);

        // When
        boolean result = Extents.isInfinite(exp);

        // Then
        assertFalse(result);
    }

    @Test
    void testIsInfinite_NotCallToZ_EXTENT() {
        // Given
        Core.Exp exp = mock(Core.Exp.class);

        when(exp.isCallTo(BuiltIn.Z_EXTENT)).thenReturn(false);

        // When
        boolean result = Extents.isInfinite(exp);

        // Then
        assertFalse(result);
    }
}
