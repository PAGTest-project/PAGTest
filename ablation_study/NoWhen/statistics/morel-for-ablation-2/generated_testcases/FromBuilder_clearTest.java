
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.PrimitiveType;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FromBuilder_clearTest {
    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;
    private List<Core.FromStep> steps;
    private List<Binding> bindings;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
        steps = new ArrayList<>();
        bindings = new ArrayList<>();
    }

    @Test
    public void testClear() {
        // Given
        fromBuilder.addAll(steps);
        fromBuilder.bindings().addAll(bindings);
        fromBuilder.addStep(new Core.Scan(bindings, new Core.IdPat(PrimitiveType.INT, "i", 0), new Core.IntLiteral(1), new Core.BoolLiteral(true)));

        // When
        fromBuilder.clear();

        // Then
        assertTrue(fromBuilder.bindings().isEmpty());
        assertTrue(fromBuilder.build().steps.isEmpty());
    }

    @Test
    public void testClearWithStepsAndBindings() {
        // Given
        steps.add(new Core.Scan(bindings, new Core.IdPat(PrimitiveType.INT, "i", 0), new Core.IntLiteral(1), new Core.BoolLiteral(true)));
        bindings.add(Binding.of(new Core.IdPat(PrimitiveType.INT, "i", 0)));
        fromBuilder.addAll(steps);
        fromBuilder.bindings().addAll(bindings);

        // When
        fromBuilder.clear();

        // Then
        assertTrue(fromBuilder.bindings().isEmpty());
        assertTrue(fromBuilder.build().steps.isEmpty());
    }
}
