
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_skipTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
    }

    @Test
    public void testSkipZero() {
        Core.Exp count = Core.Literal.of(BigDecimal.ZERO);
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testSkipNonZero() {
        Core.Exp count = Core.Literal.of(BigDecimal.ONE);
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testSkipNonLiteral() {
        Core.Exp count = Core.Id.of("someId");
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }
}
