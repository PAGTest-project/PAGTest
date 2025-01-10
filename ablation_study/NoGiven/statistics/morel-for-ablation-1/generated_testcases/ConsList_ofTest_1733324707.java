
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsList_ofTest {

    @Test
    public void testOfWithConsList() {
        // Given
        List<String> rest = new ConsList<>("b", ImmutableList.of("c"));

        // When
        List<String> result = ConsList.of("a", rest);

        // Then
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    public void testOfWithImmutableList() {
        // Given
        List<String> rest = ImmutableList.of("b", "c");

        // When
        List<String> result = ConsList.of("a", rest);

        // Then
        assertEquals(Arrays.asList("a", "b", "c"), result);
    }

    @Test
    public void testOfWithEmptyImmutableList() {
        // Given
        List<String> rest = ImmutableList.of();

        // When
        List<String> result = ConsList.of("a", rest);

        // Then
        assertEquals(Arrays.asList("a"), result);
    }
}
