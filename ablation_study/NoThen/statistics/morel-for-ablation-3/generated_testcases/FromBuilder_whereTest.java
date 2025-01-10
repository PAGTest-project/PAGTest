
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
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
        fromBuilder = new FromBuilder(typeSystem, Environments.create(typeSystem));
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
        Core.Exp condition = core.id(new Core.IdPat(typeSystem.createType(), "x", 0));
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }
}
