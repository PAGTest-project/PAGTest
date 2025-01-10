
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static net.hydromatic.morel.ast.CoreBuilder.core;
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
    public void testSkipWithZero() {
        Core.Exp count = core.intLiteral(BigDecimal.ZERO);
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testSkipWithNonZero() {
        Core.Exp count = core.intLiteral(BigDecimal.ONE);
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testSkipWithNonLiteral() {
        Core.IdPat idPat = new Core.IdPat(typeSystem.INT, "count", 0);
        Core.Exp count = core.id(idPat);
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }
}
