
package net.hydromatic.morel.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsList_sizeTest {

    @Test
    public void testSize() {
        // Given
        ConsList<Integer> consList = ConsList.of(1, 2, 3);

        // When
        int size = consList.size();

        // Then
        assertEquals(3, size);
    }
}
