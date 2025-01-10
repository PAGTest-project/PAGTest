
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Extents_createTest {

    @Test
    void testCreate() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat() {};
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Iterable<? extends Core.FromStep> followingSteps = new Iterable<Core.FromStep>() {
            @Override
            public java.util.Iterator<Core.FromStep> iterator() {
                return java.util.Collections.emptyIterator();
            }
        };
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();

        // When
        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertNotNull(result);
        assertEquals(boundPats, result.boundPats);
        assertNotNull(result.extentExp);
        assertNotNull(result.satisfiedFilters);
        assertNotNull(result.remainingFilters);
    }

    @Test
    void testCreateWithFollowingSteps() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat() {};
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Iterable<? extends Core.FromStep> followingSteps = new Iterable<Core.FromStep>() {
            @Override
            public java.util.Iterator<Core.FromStep> iterator() {
                return java.util.Collections.singletonList(new Core.Where(null, null)).iterator();
            }
        };
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();

        // When
        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertNotNull(result);
        assertEquals(boundPats, result.boundPats);
        assertNotNull(result.extentExp);
        assertNotNull(result.satisfiedFilters);
        assertNotNull(result.remainingFilters);
    }

    @Test
    void testCreateWithNonEmptyFoo() {
        // Given
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = new Core.Pat() {};
        SortedMap<Core.NamedPat, Core.Exp> boundPats = new TreeMap<>();
        Iterable<? extends Core.FromStep> followingSteps = new Iterable<Core.FromStep>() {
            @Override
            public java.util.Iterator<Core.FromStep> iterator() {
                return java.util.Collections.emptyIterator();
            }
        };
        PairList<Core.IdPat, Core.Exp> idPats = ImmutablePairList.of();
        Extent extent = new Extent(typeSystem, pat, boundPats, idPats);
        ExtentMap map = new ExtentMap();
        map.map.put(pat, ImmutablePairList.of(new Core.Exp() {}, new Core.Exp() {}));

        // When
        Analysis result = Extents.create(typeSystem, pat, boundPats, followingSteps, idPats);

        // Then
        assertNotNull(result);
        assertEquals(boundPats, result.boundPats);
        assertNotNull(result.extentExp);
        assertNotNull(result.satisfiedFilters);
        assertNotNull(result.remainingFilters);
    }
}
