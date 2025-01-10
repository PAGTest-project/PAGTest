
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionUtils_permutationsTest {

    @Test
    public void testPermutations() {
        // Given
        Collection<Integer> input = Arrays.asList(1, 2, 3);

        // When
        Collection<List<Integer>> result = CollectionUtils.permutations(input);

        // Then
        assertNotNull(result);
        assertEquals(6, result.size()); // 3! = 6 permutations
    }

    @Test
    public void testPermutationsWithNullCollection() {
        // Given
        Collection<Integer> input = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> CollectionUtils.permutations(input));
    }
}
