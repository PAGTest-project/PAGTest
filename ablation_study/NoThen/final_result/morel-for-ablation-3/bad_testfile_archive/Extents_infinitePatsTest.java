
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.ast.FromBuilder;
import net.hydromatic.morel.ast.Shuttle;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.util.ImmutablePairList;
import net.hydromatic.morel.util.Ord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;

public class Extents_infinitePatsTest {

    @Test
    public void testInfinitePatsWithInfiniteScan() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Core.Decl node = mock(Core.Decl.class);
        Core.From from = mock(Core.From.class);
        Core.Scan scan = mock(Core.Scan.class);
        Core.Exp exp = mock(Core.Exp.class);
        Core.FromStep step = mock(Core.FromStep.class);
        List<Core.FromStep> steps = List.of(step);
        Ord<Core.FromStep> ordStep = new Ord<>(0, step);
        List<Ord<Core.FromStep>> ordSteps = List.of(ordStep);

        when(node.accept(any(Shuttle.class))).thenReturn(from);
        when(from.steps).thenReturn(steps);
        when(step.e).thenReturn(scan);
        when(scan.exp).thenReturn(exp);
        when(Extents.isInfinite(exp)).thenReturn(true);

        FromBuilder fromBuilder = mock(FromBuilder.class);
        Core.From modifiedFrom = mock(Core.From.class);
        when(core.fromBuilder(typeSystem)).thenReturn(fromBuilder);
        when(fromBuilder.build()).thenReturn(modifiedFrom);

        // When
        Core.Decl result = Extents.infinitePats(typeSystem, node);

        // Then
        assertEquals(modifiedFrom, result);
        verify(fromBuilder).scan(any(Core.Pat.class), any(Core.Exp.class), any(Core.Exp.class));
    }

    @Test
    public void testInfinitePatsWithoutInfiniteScan() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Core.Decl node = mock(Core.Decl.class);
        Core.From from = mock(Core.From.class);
        Core.Scan scan = mock(Core.Scan.class);
        Core.Exp exp = mock(Core.Exp.class);
        Core.FromStep step = mock(Core.FromStep.class);
        List<Core.FromStep> steps = List.of(step);
        Ord<Core.FromStep> ordStep = new Ord<>(0, step);
        List<Ord<Core.FromStep>> ordSteps = List.of(ordStep);

        when(node.accept(any(Shuttle.class))).thenReturn(from);
        when(from.steps).thenReturn(steps);
        when(step.e).thenReturn(scan);
        when(scan.exp).thenReturn(exp);
        when(Extents.isInfinite(exp)).thenReturn(false);

        // When
        Core.Decl result = Extents.infinitePats(typeSystem, node);

        // Then
        assertEquals(from, result);
    }
}
