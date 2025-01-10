
package net.hydromatic.morel.ast;

import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.compile.Environment;
import net.hydromatic.morel.type.Binding;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        env = new Environment();
        steps = new ArrayList<>();
        bindings = new ArrayList<>();
        fromBuilder = new FromBuilder(typeSystem, env);
    }

    @Test
    public void testClear() {
        // Given
        fromBuilder.addAll(steps);
        fromBuilder.scan(new Core.IdPat("pat"), new Core.Exp() {
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
        assertEquals(Integer.MIN_VALUE, fromBuilder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, fromBuilder.removeIfLastIndex);
    }
}
