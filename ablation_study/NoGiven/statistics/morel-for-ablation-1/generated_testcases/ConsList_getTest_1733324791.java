
package net.hydromatic.morel.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

class ConsList_getTest {

    @Test
    void testGet() {
        // Given
        List<String> rest = Arrays.asList("b", "c");
        ConsList<String> consList = ConsList.of("a", rest);

        // When
        String result1 = consList.get(0);
        String result2 = consList.get(1);
        String result3 = consList.get(2);

        // Then
        assertEquals("a", result1);
        assertEquals("b", result2);
        assertEquals("c", result3);
    }
}
