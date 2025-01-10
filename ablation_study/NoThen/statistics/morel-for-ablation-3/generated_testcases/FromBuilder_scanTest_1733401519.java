
package net.hydromatic.morel.ast;

import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.Range;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FromBuilder_scanTest {

    private TypeSystem typeSystem;
    private FromBuilder fromBuilder;
    private Core.Pat pat;

    @BeforeEach
    void setUp() {
        typeSystem = mock(TypeSystem.class);
        fromBuilder = new FromBuilder(typeSystem, null);
        pat = mock(Core.Pat.class);
    }

    @Test
    void testScan() {
        // Given
        Core.Exp extent = mock(Core.Exp.class);
        when(typeSystem.extent(typeSystem, pat.type, ImmutableRangeSet.of(Range.all()))).thenReturn(extent);

        // When
        FromBuilder result = fromBuilder.scan(pat);

        // Then
        assertNotNull(result);
    }
}
