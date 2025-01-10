
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.type.RangeExtent;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Extents_isInfiniteTest {

    @Test
    void testIsInfinite_NonZExtent() {
        Core.Exp exp = mock(Core.Exp.class);
        when(exp.isCallTo(BuiltIn.Z_EXTENT)).thenReturn(false);

        assertFalse(Extents.isInfinite(exp));
    }

    @Test
    void testIsInfinite_ZExtentWithNullIterable() {
        Core.Exp exp = mock(Core.Exp.class);
        Core.Apply apply = mock(Core.Apply.class);
        Core.Literal literal = mock(Core.Literal.class);
        RangeExtent rangeExtent = mock(RangeExtent.class);

        when(exp.isCallTo(BuiltIn.Z_EXTENT)).thenReturn(true);
        when(exp.unwrap(Core.Apply.class)).thenReturn(apply);
        when(apply.arg).thenReturn(literal);
        when(literal.unwrap(RangeExtent.class)).thenReturn(rangeExtent);
        when(rangeExtent.iterable).thenReturn(null);

        assertTrue(Extents.isInfinite(exp));
    }

    @Test
    void testIsInfinite_ZExtentWithNonNullIterable() {
        Core.Exp exp = mock(Core.Exp.class);
        Core.Apply apply = mock(Core.Apply.class);
        Core.Literal literal = mock(Core.Literal.class);
        RangeExtent rangeExtent = mock(RangeExtent.class);

        when(exp.isCallTo(BuiltIn.Z_EXTENT)).thenReturn(true);
        when(exp.unwrap(Core.Apply.class)).thenReturn(apply);
        when(apply.arg).thenReturn(literal);
        when(literal.unwrap(RangeExtent.class)).thenReturn(rangeExtent);
        when(rangeExtent.iterable).thenReturn(mock(Iterable.class));

        assertFalse(Extents.isInfinite(exp));
    }
}
