
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.ast.FromBuilder;
import net.hydromatic.morel.ast.Shuttle;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.util.ImmutablePairList;
import net.hydromatic.morel.util.Ord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class Extents_infinitePatsTest {

    @Test
    public void testInfinitePats_WithInfiniteScan() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Core.Decl node = mock(Core.Decl.class);
        Core.From from = mock(Core.From.class);
        Core.Scan scan = mock(Core.Scan.class);
        Core.Exp exp = mock(Core.Exp.class);
        Core.Pat pat = mock(Core.Pat.class);
        Core.FromStep step2 = mock(Core.FromStep.class);
        List<Core.FromStep> steps = List.of(step2);
        Ord<Core.FromStep> ordStep = new Ord<>(0, scan);
        List<Ord<Core.FromStep>> ordSteps = List.of(ordStep);

        when(node.accept(any(Shuttle.class))).thenReturn(from);
        when(from.steps).thenReturn(steps);
        when(scan.exp).thenReturn(exp);
        when(scan.pat).thenReturn(pat);
        when(step2.equals(scan)).thenReturn(false);
        when(step2 instanceof Core.Where).thenReturn(false);
        when(Extents.isInfinite(exp)).thenReturn(true);

        FromBuilder fromBuilder = mock(FromBuilder.class);
        when(core.fromBuilder(typeSystem)).thenReturn(fromBuilder);
        when(fromBuilder.build()).thenReturn(from);

        Analysis analysis = mock(Analysis.class);
        when(analysis.extentExp).thenReturn(exp);
        when(analysis.satisfiedFilters).thenReturn(List.of());

        when(Extents.create(eq(typeSystem), eq(pat), any(), any(), any())).thenReturn(analysis);

        // When
        Core.Decl result = Extents.infinitePats(typeSystem, node);

        // Then
        assertEquals(from, result);
        verify(fromBuilder).scan(eq(pat), eq(exp), any());
        verify(fromBuilder).addAll(eq(steps));
        verify(fromBuilder).build();
    }

    @Test
    public void testInfinitePats_WithoutInfiniteScan() {
        // Given
        TypeSystem typeSystem = mock(TypeSystem.class);
        Core.Decl node = mock(Core.Decl.class);
        Core.From from = mock(Core.From.class);
        Core.Scan scan = mock(Core.Scan.class);
        Core.Exp exp = mock(Core.Exp.class);
        Core.FromStep step2 = mock(Core.FromStep.class);
        List<Core.FromStep> steps = List.of(step2);
        Ord<Core.FromStep> ordStep = new Ord<>(0, scan);
        List<Ord<Core.FromStep>> ordSteps = List.of(ordStep);

        when(node.accept(any(Shuttle.class))).thenReturn(from);
        when(from.steps).thenReturn(steps);
        when(scan.exp).thenReturn(exp);
        when(Extents.isInfinite(exp)).thenReturn(false);

        // When
        Core.Decl result = Extents.infinitePats(typeSystem, node);

        // Then
        assertEquals(from, result);
        verifyNoInteractions(core.fromBuilder(typeSystem));
    }
}
