
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

public class Extents_infinitePatsTest {

    @Test
    public void testInfinitePatsWithInfiniteScan() {
        TypeSystem typeSystem = mock(TypeSystem.class);
        Core.Decl node = mock(Core.Decl.class);
        Core.From from = mock(Core.From.class);
        Core.Scan scan = mock(Core.Scan.class);
        Core.Exp exp = mock(Core.Exp.class);
        Core.FromStep step = mock(Core.FromStep.class);
        Ord<Core.FromStep> ordStep = new Ord<>(0, step);
        FromBuilder fromBuilder = mock(FromBuilder.class);
        Core.From builtFrom = mock(Core.From.class);

        when(node.accept(any(Shuttle.class))).thenReturn(from);
        when(from.steps).thenReturn(ImmutablePairList.of(ordStep));
        when(step.e).thenReturn(scan);
        when(scan.exp).thenReturn(exp);
        when(Extents.isInfinite(exp)).thenReturn(true);
        when(core.fromBuilder(typeSystem)).thenReturn(fromBuilder);
        when(fromBuilder.build()).thenReturn(builtFrom);

        Core.Decl result = Extents.infinitePats(typeSystem, node);

        assertEquals(builtFrom, result);
        verify(fromBuilder).scan(any(Core.Pat.class), any(Core.Exp.class), any(Core.Exp.class));
        verify(fromBuilder).build();
    }

    @Test
    public void testInfinitePatsWithNonInfiniteScan() {
        TypeSystem typeSystem = mock(TypeSystem.class);
        Core.Decl node = mock(Core.Decl.class);
        Core.From from = mock(Core.From.class);
        Core.Scan scan = mock(Core.Scan.class);
        Core.Exp exp = mock(Core.Exp.class);
        Core.FromStep step = mock(Core.FromStep.class);
        Ord<Core.FromStep> ordStep = new Ord<>(0, step);

        when(node.accept(any(Shuttle.class))).thenReturn(from);
        when(from.steps).thenReturn(ImmutablePairList.of(ordStep));
        when(step.e).thenReturn(scan);
        when(scan.exp).thenReturn(exp);
        when(Extents.isInfinite(exp)).thenReturn(false);

        Core.Decl result = Extents.infinitePats(typeSystem, node);

        assertEquals(from, result);
        verifyNoInteractions(core.fromBuilder(typeSystem));
    }
}
