
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environment;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_yield_Test {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;
    private Environment env;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        env = null; // or initialize with a valid Environment if needed
        fromBuilder = new FromBuilder(typeSystem, env);
    }

    @Test
    public void testYield_IdentityTupleSingleton() {
        Core.Tuple tuple = new Core.Tuple(Arrays.asList(new Core.Id("x")));
        List<Binding> bindings = Arrays.asList(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = fromBuilder.yield_(false, bindings, tuple);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testYield_IdentityTupleNonSingleton() {
        Core.Tuple tuple = new Core.Tuple(Arrays.asList(new Core.Id("x"), new Core.Id("y")));
        List<Binding> bindings = Arrays.asList(
                new Binding(new Core.IdPat("x"), null, null, false),
                new Binding(new Core.IdPat("y"), null, null, false)
        );
        FromBuilder result = fromBuilder.yield_(false, bindings, tuple);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testYield_RenameTupleSingleton() {
        Core.Tuple tuple = new Core.Tuple(Arrays.asList(new Core.Id("y")));
        List<Binding> bindings = Arrays.asList(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = fromBuilder.yield_(false, bindings, tuple);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testYield_RenameTupleNonSingleton() {
        Core.Tuple tuple = new Core.Tuple(Arrays.asList(new Core.Id("y"), new Core.Id("z")));
        List<Binding> bindings = Arrays.asList(
                new Binding(new Core.IdPat("x"), null, null, false),
                new Binding(new Core.IdPat("y"), null, null, false)
        );
        FromBuilder result = fromBuilder.yield_(false, bindings, tuple);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testYield_IdWithMatchingBinding() {
        Core.Id id = new Core.Id("x");
        List<Binding> bindings = Arrays.asList(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = fromBuilder.yield_(false, bindings, id);
        assertEquals(fromBuilder, result);
    }

    @Test
    public void testYield_IdWithNonMatchingBinding() {
        Core.Id id = new Core.Id("y");
        List<Binding> bindings = Arrays.asList(new Binding(new Core.IdPat("x"), null, null, false));
        FromBuilder result = fromBuilder.yield_(false, bindings, id);
        assertEquals(fromBuilder, result);
    }
}
