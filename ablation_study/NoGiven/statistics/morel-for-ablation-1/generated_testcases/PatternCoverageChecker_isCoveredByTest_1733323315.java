
package net.hydromatic.morel.compile;

import net.hydromatic.morel.ast.Core;
import net.hydromatic.morel.type.TypeSystem;
import net.hydromatic.morel.util.Sat;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PatternCoverageChecker_isCoveredByTest {

    @Test
    void testIsCoveredBy() {
        TypeSystem typeSystem = new TypeSystem();
        Core.Pat pat = Core.Pat.builder().build();
        List<Core.Pat> patList = Arrays.asList(Core.Pat.builder().build(), Core.Pat.builder().build());

        boolean result = PatternCoverageChecker.isCoveredBy(typeSystem, patList, pat);

        assertTrue(result);
    }
}
