
package net.hydromatic.morel.ast;

import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.Range;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class FromBuilder_scanTest {

    @Test
    void testScan() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Core.Pat pat = mock(Core.Pat.class);
        Core.Exp extent = mock(Core.Exp.class);
        Core.Exp boolLiteral = mock(Core.Exp.class);
        CoreBuilder core = mock(CoreBuilder.class);
        FromBuilder fromBuilder = new FromBuilder(typeSystem, null);

        when(core.extent(typeSystem, pat.type, ImmutableRangeSet.of(Range.all()))).thenReturn(extent);
        when(core.boolLiteral(true)).thenReturn(boolLiteral);

        // When
        FromBuilder result = fromBuilder.scan(pat);

        // Then
        assertNotNull(result);
        verify(core).extent(typeSystem, pat.type, ImmutableRangeSet.of(Range.all()));
        verify(core).boolLiteral(true);
    }
}
