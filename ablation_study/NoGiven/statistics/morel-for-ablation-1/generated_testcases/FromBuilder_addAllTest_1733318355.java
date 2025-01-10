
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
    private List<Core.FromStep> steps;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
        pat = new Core.IdPat(PrimitiveType.INT, "id", 0);
        exp = new Core.Literal(Op.INT_LITERAL, PrimitiveType.INT, 1);
        steps = Arrays.asList(new Core.Scan(Arrays.asList(Binding.of(pat)), pat, exp, exp));
    }

    @Test
    public void testAddAll() {
        fromBuilder.clear();
        FromBuilder result = fromBuilder.addAll(steps);
        assertEquals(fromBuilder, result);
        assertEquals(steps, fromBuilder.bindings());
    }
}
