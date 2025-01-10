
package net.hydromatic.morel.ast;

import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.compile.Environment;
import net.hydromatic.morel.type.Binding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_clearTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;
    private Environment env;
    private List<Core.FromStep> steps;
    private List<Binding> bindings;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        env = new Environment() {
            @Override
            public Environment bindAll(List<Binding> bindings) {
                return this;
            }

            @Override
            public int distance(int ordinal, Core.NamedPat pat) {
                return 0;
            }

            @Override
            public Environment nearestAncestorNotObscuredBy(Set<Core.NamedPat> pats) {
                return this;
            }

            @Override
            public void visit(Consumer<Binding> consumer) {
            }

            @Override
            public Binding getOpt(Core.NamedPat pat) {
                return null;
            }

            @Override
            public Binding getOpt(String name) {
                return null;
            }
        };
        steps = new ArrayList<>();
        bindings = new ArrayList<>();
        fromBuilder = new FromBuilder(typeSystem, env);
    }

    @Test
    public void testClear() {
        // Given
        fromBuilder.addAll(steps);
        fromBuilder.scan(new Core.IdPat(typeSystem, "pat", 0), new Core.Exp() {
            @Override
            public Op op() {
                return Op.FROM;
            }
        });
        fromBuilder.yield_(false, new Core.Exp() {
            @Override
            public Op op() {
                return Op.ID;
            }
        });

        // When
        fromBuilder.clear();

        // Then
        assertEquals(0, fromBuilder.bindings().size());
    }
}
