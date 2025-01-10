
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environment;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

class FromBuilder_skipTest {

    private FromBuilder fromBuilder;
    private Core.Exp count;

    @BeforeEach
    void setUp() {
        TypeSystem typeSystem = Mockito.mock(TypeSystem.class);
        Environment env = Mockito.mock(Environment.class);
        fromBuilder = new FromBuilder(typeSystem, env);
        count = Mockito.mock(Core.Exp.class);
    }

    @Test
    void testSkipZero() {
        // Given
        when(count.op).thenReturn(Core.Op.INT_LITERAL);
        Core.Literal literal = Mockito.mock(Core.Literal.class);
        when(count.unwrap(Core.Literal.class)).thenReturn(literal);
        when(literal.value).thenReturn(BigDecimal.ZERO);

        // When
        FromBuilder result = fromBuilder.skip(count);

        // Then
        assertSame(fromBuilder, result);
    }

    @Test
    void testSkipNonZero() {
        // Given
        when(count.op).thenReturn(Core.Op.INT_LITERAL);
        Core.Literal literal = Mockito.mock(Core.Literal.class);
        when(count.unwrap(Core.Literal.class)).thenReturn(literal);
        when(literal.value).thenReturn(BigDecimal.ONE);

        // When
        FromBuilder result = fromBuilder.skip(count);

        // Then
        assertSame(fromBuilder, result);
    }
}
