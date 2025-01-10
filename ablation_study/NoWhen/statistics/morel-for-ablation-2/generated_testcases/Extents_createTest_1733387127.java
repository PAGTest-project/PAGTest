
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.util.ImmutablePairList;
import net.hydromatic.morel.util.Pair;
import net.hydromatic.morel.util.PairList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Extents_createTest {

    @Test
    public void testCreate_AllPathsCovered() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Iterable<? extends Core.FromStep> followingSteps = new ArrayList<>();
        PairList<Core.IdPat, Core.Exp> idPats = PairList.of();

        // When
        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertNotNull(result);
        assertNotNull(result.boundPats);
        assertNotNull(result.goalPats);
        assertNotNull(result.extentExp);
        assertNotNull(result.satisfiedFilters);
        assertNotNull(result.remainingFilters);
    }

    @Test
    public void testCreate_WithFollowingSteps() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        ArrayList<Core.FromStep> followingSteps = new ArrayList<>();
        followingSteps.add(new Core.Where());
        PairList<Core.IdPat, Core.Exp> idPats = PairList.of();

        // When
        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertNotNull(result);
        assertNotNull(result.boundPats);
        assertNotNull(result.goalPats);
        assertNotNull(result.extentExp);
        assertNotNull(result.satisfiedFilters);
        assertNotNull(result.remainingFilters);
    }

    @Test
    public void testCreate_WithBoundPats() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        boundPats.put(new Core.NamedPat(), new Core.Exp());
        Iterable<? extends Core.FromStep> followingSteps = new ArrayList<>();
        PairList<Core.IdPat, Core.Exp> idPats = PairList.of();

        // When
        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertNotNull(result);
        assertNotNull(result.boundPats);
        assertNotNull(result.goalPats);
        assertNotNull(result.extentExp);
        assertNotNull(result.satisfiedFilters);
        assertNotNull(result.remainingFilters);
    }

    @Test
    public void testCreate_WithIdPats() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat();
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Iterable<? extends Core.FromStep> followingSteps = new ArrayList<>();
        PairList<Core.IdPat, Core.Exp> idPats = PairList.of(new Core.IdPat(), new Core.Exp());

        // When
        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertNotNull(result);
        assertNotNull(result.boundPats);
        assertNotNull(result.goalPats);
        assertNotNull(result.extentExp);
        assertNotNull(result.satisfiedFilters);
        assertNotNull(result.remainingFilters);
    }
}
