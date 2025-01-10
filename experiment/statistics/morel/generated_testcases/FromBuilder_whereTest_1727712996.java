
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environment;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_whereTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;
    private Environment env;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        env = new Environment();
        fromBuilder = new FromBuilder(typeSystem, env);
    }

    @Test
    public void testWhereWithTrueCondition() {
        Core.Exp trueCondition = core.boolLiteral(true);
        FromBuilder result = fromBuilder.where(trueCondition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithFalseCondition() {
        Core.Exp falseCondition = core.boolLiteral(false);
        FromBuilder result = fromBuilder.where(falseCondition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithNonLiteralCondition() {
        Core.Exp nonLiteralCondition = core.id(new Core.IdPat("someId"));
        FromBuilder result = fromBuilder.where(nonLiteralCondition);
        assertEquals(fromBuilder, result);
    }
}
