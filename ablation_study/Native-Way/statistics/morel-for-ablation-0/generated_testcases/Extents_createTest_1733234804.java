
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.util.ImmutablePairList;
import net.hydromatic.morel.util.Pair;
import net.hydromatic.morel.util.PairList;
import org.junit.jupiter.api.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Extents_createTest {

    @Test
    void testCreateWithEmptyFollowingSteps() {
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Iterable<? extends Core.FromStep> followingSteps = ImmutablePairList.of();
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();

        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        assertTrue(result.extentExp.isConstant());
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }

    @Test
    void testCreateWithNonEmptyFollowingSteps() {
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Core.FromStep step = new Core.Where();
        Iterable<? extends Core.FromStep> followingSteps = ImmutablePairList.of(step);
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();

        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        assertTrue(result.extentExp.isConstant());
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }

    @Test
    void testCreateWithNonEmptyBoundPats() {
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat();
        Core.NamedPat namedPat = new Core.NamedPat();
        Core.Exp exp = new Core.Exp();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        boundPats.put(namedPat, exp);
        Iterable<? extends Core.FromStep> followingSteps = ImmutablePairList.of();
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();

        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        assertEquals(exp, result.boundPats.get(namedPat));
        assertTrue(result.extentExp.isConstant());
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }
}
