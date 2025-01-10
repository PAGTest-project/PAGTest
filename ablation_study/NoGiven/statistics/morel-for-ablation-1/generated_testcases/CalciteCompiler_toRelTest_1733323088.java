
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.foreign.Calcite;
import org.apache.calcite.rel.RelNode;
import org.apache.calcite.tools.RelBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CalciteCompiler_toRelTest {

    @Test
    void testToRelWithNonNullRelNode() {
        // Given
        Calcite calcite = mock(Calcite.class);
        RelBuilder relBuilder = mock(RelBuilder.class);
        when(calcite.relBuilder()).thenReturn(relBuilder);
        when(relBuilder.build()).thenReturn(mock(RelNode.class));

        CalciteCompiler compiler = new CalciteCompiler(null, calcite);
        Environment env = mock(Environment.class);
        Core.Exp expression = mock(Core.Exp.class);

        // When
        RelNode result = compiler.toRel(env, expression);

        // Then
        assertNotNull(result);
    }

    @Test
    void testToRelWithNullRelNode() {
        // Given
        Calcite calcite = mock(Calcite.class);
        RelBuilder relBuilder = mock(RelBuilder.class);
        when(calcite.relBuilder()).thenReturn(relBuilder);
        when(relBuilder.build()).thenReturn(null);

        CalciteCompiler compiler = new CalciteCompiler(null, calcite);
        Environment env = mock(Environment.class);
        Core.Exp expression = mock(Core.Exp.class);

        // When
        RelNode result = compiler.toRel(env, expression);

        // Then
        assertNull(result);
    }
}
