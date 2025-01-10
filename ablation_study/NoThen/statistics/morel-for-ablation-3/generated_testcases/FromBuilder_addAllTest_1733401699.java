
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_addAllTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;
    private Core.Pat pat;
    private Core.Exp exp;
    private Core.Exp condition;
    private List<Core.FromStep> steps;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
        CoreBuilder coreBuilder = CoreBuilder.core();
        pat = coreBuilder.idPat("i");
        exp = coreBuilder.intLiteral(1);
        condition = coreBuilder.boolLiteral(true);
        steps = Arrays.asList(coreBuilder.scan(Binding.of(pat), pat, exp, condition));
    }

    @Test
    public void testAddAll() {
        FromBuilder result = fromBuilder.addAll(steps);
        assertEquals(fromBuilder, result);
        assertEquals(1, fromBuilder.bindings().size());
    }

    @Test
    public void testAddAllEmpty() {
        FromBuilder result = fromBuilder.addAll(Arrays.asList());
        assertEquals(fromBuilder, result);
        assertEquals(0, fromBuilder.bindings().size());
    }

    @Test
    public void testAddAllMultipleSteps() {
        CoreBuilder coreBuilder = CoreBuilder.core();
        Core.Pat pat2 = coreBuilder.idPat("j");
        Core.Exp exp2 = coreBuilder.intLiteral(2);
        Core.FromStep step2 = coreBuilder.scan(Binding.of(pat2), pat2, exp2, condition);
        steps = Arrays.asList(steps.get(0), step2);

        FromBuilder result = fromBuilder.addAll(steps);
        assertEquals(fromBuilder, result);
        assertEquals(2, fromBuilder.bindings().size());
    }
}
