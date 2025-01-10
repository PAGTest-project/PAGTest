
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_whereTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;
    private CoreBuilder core;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        core = new CoreBuilder(typeSystem);
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
    }

    @Test
    public void testWhereWithTrueCondition() {
        Core.Exp condition = core.boolLiteral(true);
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithFalseCondition() {
        Core.Exp condition = core.boolLiteral(false);
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithNonLiteralCondition() {
        Core.IdPat idPat = new Core.IdPat(PrimitiveType.INT, "x", 0);
        Core.Exp condition = core.id(idPat);
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }
}
