
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

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
    public void testSkipWithNonIntLiteral() {
        Core.Exp count = core.id(new Core.IdPat("count", PrimitiveType.INT, 0));
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testSkipAfterScan() {
        Core.Pat pat = new Core.IdPat("x", PrimitiveType.INT, 0);
        Core.Exp exp = core.list(typeSystem, core.intLiteral(BigDecimal.ONE), core.intLiteral(BigDecimal.valueOf(2)));
        fromBuilder.scan(pat, exp);

        Core.Exp count = core.intLiteral(BigDecimal.ONE);
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testSkipAfterWhere() {
        Core.Pat pat = new Core.IdPat("x", PrimitiveType.INT, 0);
        Core.Exp exp = core.list(typeSystem, core.intLiteral(BigDecimal.ONE), core.intLiteral(BigDecimal.valueOf(2)));
        fromBuilder.scan(pat, exp);

        Core.Exp condition = core.greaterThan(typeSystem, core.id(new Core.IdPat("x", PrimitiveType.INT, 0)), core.intLiteral(BigDecimal.ZERO));
        fromBuilder.where(condition);

        Core.Exp count = core.intLiteral(BigDecimal.ONE);
        FromBuilder result = fromBuilder.skip(count);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testSkipWithTake() {
        Core.Pat pat = new Core.IdPat("x", PrimitiveType.INT, 0);
        Core.Exp exp = core.list(typeSystem, core.intLiteral(BigDecimal.ONE), core.intLiteral(BigDecimal.valueOf(2)));
        fromBuilder.scan(pat, exp);

        Core.Exp count = core.intLiteral(BigDecimal.ONE);
        fromBuilder.skip(count);

        Core.Exp takeCount = core.intLiteral(BigDecimal.ONE);
        FromBuilder result = fromBuilder.take(takeCount);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testSkipWithBuildSimplify() {
        Core.Pat pat = new Core.IdPat("x", PrimitiveType.INT, 0);
        Core.Exp exp = core.list(typeSystem, core.intLiteral(BigDecimal.ONE), core.intLiteral(BigDecimal.valueOf(2)));
        fromBuilder.scan(pat, exp);

        Core.Exp count = core.intLiteral(BigDecimal.ONE);
        fromBuilder.skip(count);

        Core.Exp result = fromBuilder.buildSimplify();
        assertEquals(fromBuilder.build(), result);
    }
}
