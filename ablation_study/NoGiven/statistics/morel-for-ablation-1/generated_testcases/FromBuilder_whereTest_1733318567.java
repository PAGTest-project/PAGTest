
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static net.hydromatic.morel.ast.CoreBuilder.core;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_whereTest {
    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
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
        Core.Exp condition = core.id(new Core.IdPat(typeSystem.getType("int"), "x", 0));
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithComplexCondition() {
        Core.Exp condition = core.greaterThan(typeSystem, core.id(new Core.IdPat(typeSystem.getType("int"), "x", 0)), core.intLiteral(10));
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithEmptySteps() {
        Core.Exp condition = core.boolLiteral(false);
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithNonEmptySteps() {
        fromBuilder.scan(new Core.IdPat(typeSystem.getType("int"), "x", 0), core.list(typeSystem, core.intLiteral(1), core.intLiteral(2)));
        Core.Exp condition = core.boolLiteral(false);
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithMultipleSteps() {
        fromBuilder.scan(new Core.IdPat(typeSystem.getType("int"), "x", 0), core.list(typeSystem, core.intLiteral(1), core.intLiteral(2)));
        fromBuilder.scan(new Core.IdPat(typeSystem.getType("int"), "y", 0), core.list(typeSystem, core.intLiteral(3), core.intLiteral(4)));
        Core.Exp condition = core.boolLiteral(false);
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithClearBefore() {
        fromBuilder.scan(new Core.IdPat(typeSystem.getType("int"), "x", 0), core.list(typeSystem, core.intLiteral(1), core.intLiteral(2)));
        fromBuilder.clear();
        Core.Exp condition = core.boolLiteral(false);
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testWhereWithAddAllBefore() {
        Core.FromStep step1 = core.scan(List.of(Binding.of(new Core.IdPat(typeSystem.getType("int"), "x", 0))), new Core.IdPat(typeSystem.getType("int"), "x", 0), core.list(typeSystem, core.intLiteral(1), core.intLiteral(2)), core.boolLiteral(true));
        Core.FromStep step2 = core.scan(List.of(Binding.of(new Core.IdPat(typeSystem.getType("int"), "y", 0))), new Core.IdPat(typeSystem.getType("int"), "y", 0), core.list(typeSystem, core.intLiteral(3), core.intLiteral(4)), core.boolLiteral(true));
        fromBuilder.addAll(List.of(step1, step2));
        Core.Exp condition = core.boolLiteral(false);
        FromBuilder result = fromBuilder.where(condition);
        assertEquals(fromBuilder, result);
    }
}
