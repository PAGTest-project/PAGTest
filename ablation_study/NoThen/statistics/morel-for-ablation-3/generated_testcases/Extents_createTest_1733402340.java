
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.ast.CoreBuilder;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.util.ImmutablePairList;
import net.hydromatic.morel.util.Pair;
import net.hydromatic.morel.util.PairList;
import com.google.common.collect.ImmutableRangeSet;
import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Extents_createTest {

    @Test
    public void testCreate_AllPathsCovered() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = CoreBuilder.core.pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Iterable<? extends Core.FromStep> followingSteps = new ArrayList<>();
        PairList<Core.IdPat, Core.Exp> idPats = PairList.of();

        // When
        Extents.Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertTrue(result.extentExp.equals(CoreBuilder.core.extent(typeSystem, pat.type, ImmutableRangeSet.of(Range.all()))));
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }

    @Test
    public void testCreate_WithFollowingSteps() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = CoreBuilder.core.pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        ArrayList<Core.FromStep> followingSteps = new ArrayList<>();
        followingSteps.add(new Core.Where(CoreBuilder.core.boolLiteral(true)));
        PairList<Core.IdPat, Core.Exp> idPats = PairList.of();

        // When
        Extents.Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertTrue(result.extentExp.equals(CoreBuilder.core.extent(typeSystem, pat.type, ImmutableRangeSet.of(Range.all()))));
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }

    @Test
    public void testCreate_WithBoundPats() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = CoreBuilder.core.pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        boundPats.put(CoreBuilder.core.namedPat("test"), CoreBuilder.core.boolLiteral(true));
        Iterable<? extends Core.FromStep> followingSteps = new ArrayList<>();
        PairList<Core.IdPat, Core.Exp> idPats = PairList.of();

        // When
        Extents.Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertTrue(result.extentExp.equals(CoreBuilder.core.extent(typeSystem, pat.type, ImmutableRangeSet.of(Range.all()))));
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }
}
