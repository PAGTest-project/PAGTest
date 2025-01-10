
package net.hydromatic.morel.ast;

import net.hydromatic.morel.util.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Pos_splitTest {

    @Test
    void testSplit_ValidInput() {
        String input = "a,b,c";
        char delimiter = ',';
        String file = "testFile";

        Pair<String, Pos> result = Pos.split(input, delimiter, file);

        assertEquals("abc", result.left);
        assertEquals(file, result.right.file);
    }

    @Test
    void testSplit_InvalidInput() {
        String input = "a,b,c,d";
        char delimiter = ',';
        String file = "testFile";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Pos.split(input, delimiter, file);
        });

        assertEquals("expected exactly two occurrences of delimiter, ','", exception.getMessage());
    }
}
