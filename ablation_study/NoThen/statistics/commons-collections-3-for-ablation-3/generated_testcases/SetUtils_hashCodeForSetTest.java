
package org.apache.commons.collections4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SetUtils_hashCodeForSetTest {

    @Test
    public void testHashCodeForSet_NullSet() {
        // Given
        Collection<Object> set = null;

        // When
        int result = SetUtils.hashCodeForSet(set);

        // Then
        assertEquals(0, result);
    }

    @Test
    public void testHashCodeForSet_EmptySet() {
        // Given
        Collection<Object> set = Collections.emptySet();

        // When
        int result = SetUtils.hashCodeForSet(set);

        // Then
        assertEquals(0, result);
    }

    @Test
    public void testHashCodeForSet_NonEmptySet() {
        // Given
        Collection<Object> set = Arrays.asList("a", "b", "c");

        // When
        int result = SetUtils.hashCodeForSet(set);

        // Then
        assertEquals("a".hashCode() + "b".hashCode() + "c".hashCode(), result);
    }

    @Test
    public void testHashCodeForSet_SetWithNullElements() {
        // Given
        Collection<Object> set = Arrays.asList("a", null, "b");

        // When
        int result = SetUtils.hashCodeForSet(set);

        // Then
        assertEquals("a".hashCode() + "b".hashCode(), result);
    }
}
