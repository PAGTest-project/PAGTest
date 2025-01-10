
package net.hydromatic.morel.ast;

import org.apache.calcite.util.mapping.IntPair;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Pos_ofTest {

    @Test
    void testOf() {
        // Given
        String ml = "abc\ndef\nghi";
        String file = "testFile";
        int startOffset = 4; // Position of 'd' in "def"
        int endOffset = 7;   // Position of 'g' in "ghi"

        // When
        Pos pos = Pos.of(ml, file, startOffset, endOffset);

        // Then
        assertEquals(file, pos.file);
        assertEquals(2, pos.startLine);
        assertEquals(1, pos.startColumn);
        assertEquals(3, pos.endLine);
        assertEquals(1, pos.endColumn);
    }

    @Test
    void testOfWithSingleLine() {
        // Given
        String ml = "abcdef";
        String file = "testFile";
        int startOffset = 2; // Position of 'c' in "abcdef"
        int endOffset = 4;   // Position of 'e' in "abcdef"

        // When
        Pos pos = Pos.of(ml, file, startOffset, endOffset);

        // Then
        assertEquals(file, pos.file);
        assertEquals(1, pos.startLine);
        assertEquals(3, pos.startColumn);
        assertEquals(1, pos.endLine);
        assertEquals(5, pos.endColumn);
    }

    @Test
    void testOfWithInvalidOffset() {
        // Given
        String ml = "abcdef";
        String file = "testFile";
        int startOffset = 10; // Invalid offset
        int endOffset = 12;   // Invalid offset

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            Pos.of(ml, file, startOffset, endOffset);
        });
    }
}
