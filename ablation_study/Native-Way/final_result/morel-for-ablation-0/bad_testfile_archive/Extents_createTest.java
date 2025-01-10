
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.ast.Op;
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
        Core.Pat pat = new Core.Pat(Op.ID_PAT, typeSystem.getType("PatType")) {};
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Iterable<? extends Core.FromStep> followingSteps = ImmutablePairList.of();
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();

        Extents.Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        assertTrue(result.extentExp.isConstant());
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }

    @Test
    void testCreateWithNonEmptyFollowingSteps() {
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat(Op.ID_PAT, typeSystem.getType("PatType")) {};
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Core.FromStep step = new Core.Where(ImmutablePairList.of(), new Core.Exp(null, Op.ID_PAT, typeSystem.getType("ExpType")) {});
        Iterable<? extends Core.FromStep> followingSteps = ImmutablePairList.of(step);
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();

        Extents.Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        assertTrue(result.extentExp.isConstant());
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }

    @Test
    void testCreateWithNonEmptyBoundPats() {
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat(Op.ID_PAT, typeSystem.getType("PatType")) {};
        Core.NamedPat namedPat = new Core.NamedPat(Op.ID_PAT, typeSystem.getType("NamedPatType"), "name", 0) {};
        Core.Exp exp = new Core.Exp(null, Op.ID_PAT, typeSystem.getType("ExpType")) {};
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        boundPats.put(namedPat, exp);
        Iterable<? extends Core.FromStep> followingSteps = ImmutablePairList.of();
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();

        Extents.Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        assertEquals(exp, result.boundPats.get(namedPat));
        assertTrue(result.extentExp.isConstant());
        assertTrue(result.satisfiedFilters.isEmpty());
        assertTrue(result.remainingFilters.isEmpty());
    }
}
