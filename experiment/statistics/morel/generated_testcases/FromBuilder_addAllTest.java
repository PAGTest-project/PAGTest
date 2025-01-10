
package net.hydromatic.morel.ast;

import net.hydromatic.morel.compile.Environments;
import net.hydromatic.morel.type.Binding;
import net.hydromatic.morel.type.TypeSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FromBuilder_addAllTest {

    private FromBuilder fromBuilder;
    private TypeSystem typeSystem;

    @BeforeEach
    public void setUp() {
        typeSystem = new TypeSystem();
        fromBuilder = new FromBuilder(typeSystem, Environments.empty());
    }

    @Test
    public void testAddAll() {
        // Given
        Core.FromStep step1 = new Core.FromStep(Op.SCAN, Arrays.asList(new Binding("id1", typeSystem.createType("type1")))) {
            @Override
            public void accept(Shuttle shuttle) {
                // Implementation of accept method
            }
        };
        Core.FromStep step2 = new Core.FromStep(Op.SCAN, Arrays.asList(new Binding("id2", typeSystem.createType("type2")))) {
            @Override
            public void accept(Shuttle shuttle) {
                // Implementation of accept method
            }
        };
        Iterable<Core.FromStep> steps = Arrays.asList(step1, step2);

        // When
        FromBuilder result = fromBuilder.addAll(steps);

        // Then
        assertEquals(2, fromBuilder.bindings().size());
        assertEquals("id1", fromBuilder.bindings().get(0).id.name);
        assertEquals("id2", fromBuilder.bindings().get(1).id.name);
        assertEquals(result, fromBuilder);
    }
}
