
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.eval.Code;
import net.hydromatic.morel.foreign.Calcite;
import org.apache.calcite.tools.RelBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalciteCompiler_compileArgTest {

    @Test
    public void testCompileArg_RelCodeAndNonRelContext() {
        // Given
        Calcite calcite = mock(Calcite.class);
        CalciteCompiler compiler = new CalciteCompiler(null, calcite);
        Context cx = mock(Context.class);
        Core.Exp expression = mock(Core.Exp.class);
        Code code = mock(Code.class);
        RelBuilder relBuilder = mock(RelBuilder.class);
        RelContext rx = mock(RelContext.class);

        when(compiler.superCompileArg(cx, expression)).thenReturn(code);
        when(code instanceof RelCode).thenReturn(true);
        when(cx instanceof RelContext).thenReturn(false);
        when(calcite.relBuilder()).thenReturn(relBuilder);
        when(rx.env).thenReturn(null);
        when(rx.relBuilder).thenReturn(relBuilder);
        when(calcite.code(rx.env, rx.relBuilder.build(), expression.type)).thenReturn(code);
        when(compiler.toRel3(rx, expression, false)).thenReturn(true);

        // When
        Code result = compiler.compileArg(cx, expression);

        // Then
        assertTrue(result instanceof Code);
    }

    @Test
    public void testCompileArg_NonRelCodeOrRelContext() {
        // Given
        Calcite calcite = mock(Calcite.class);
        CalciteCompiler compiler = new CalciteCompiler(null, calcite);
        Context cx = mock(Context.class);
        Core.Exp expression = mock(Core.Exp.class);
        Code code = mock(Code.class);

        when(compiler.superCompileArg(cx, expression)).thenReturn(code);
        when(code instanceof RelCode).thenReturn(false);
        when(cx instanceof RelContext).thenReturn(false);

        // When
        Code result = compiler.compileArg(cx, expression);

        // Then
        assertTrue(result instanceof Code);
    }
}
