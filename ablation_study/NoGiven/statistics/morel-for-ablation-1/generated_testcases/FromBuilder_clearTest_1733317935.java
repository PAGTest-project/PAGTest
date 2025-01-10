
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
        fromBuilder.scan(new Core.IdPat(PrimitiveType.INT, "i", 0), new Core.ListExp(PrimitiveType.INT, new ArrayList<>()));
        fromBuilder.yield_(new Core.Id(new Core.IdPat(PrimitiveType.INT, "i", 0)));

        // When
        fromBuilder.clear();

        // Then
        assertTrue(fromBuilder.bindings().isEmpty());
        assertEquals(Integer.MIN_VALUE, fromBuilder.removeIfNotLastIndex);
        assertEquals(Integer.MIN_VALUE, fromBuilder.removeIfLastIndex);
    }
}
