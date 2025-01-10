
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
    private Core.IdPat pat;
    private Core.Exp exp;
    private Core.Exp condition;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
        pat = new Core.IdPat(PrimitiveType.INT, "id", 0);
        exp = new Core.Literal(Op.INT_LITERAL, PrimitiveType.INT, 10);
        condition = new Core.Literal(Op.BOOL_LITERAL, PrimitiveType.BOOLEAN, true);
    }

    @Test
    public void testAddAll() {
        Core.FromStep step1 = new Core.Scan(Arrays.asList(Binding.of(pat)), pat, exp, condition);
        Core.FromStep step2 = new Core.Where(Arrays.asList(Binding.of(pat)), condition);
        List<Core.FromStep> steps = Arrays.asList(step1, step2);

        FromBuilder result = fromBuilder.addAll(steps);

        assertEquals(fromBuilder, result);
        assertEquals(2, fromBuilder.steps.size());
    }

    @Test
    public void testAddAllEmpty() {
        List<Core.FromStep> steps = Arrays.asList();

        FromBuilder result = fromBuilder.addAll(steps);

        assertEquals(fromBuilder, result);
        assertEquals(0, fromBuilder.steps.size());
    }

    @Test
    public void testAddAllSingleStep() {
        Core.FromStep step = new Core.Scan(Arrays.asList(Binding.of(pat)), pat, exp, condition);
        List<Core.FromStep> steps = Arrays.asList(step);

        FromBuilder result = fromBuilder.addAll(steps);

        assertEquals(fromBuilder, result);
        assertEquals(1, fromBuilder.steps.size());
    }
}
