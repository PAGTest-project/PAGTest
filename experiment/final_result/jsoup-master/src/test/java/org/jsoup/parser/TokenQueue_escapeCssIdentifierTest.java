
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_escapeCssIdentifierTest {

    @Test
    public void testEscapeCssIdentifier() {
        // Given
        String input = "p.p:p!p";
        String expectedOutput = "p\\.p\\:p\\!p";

        // When
        String actualOutput = TokenQueue.escapeCssIdentifier(input);

        // Then
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testEscapeCssIdentifierWithEscapedChars() {
        // Given
        String input = "p\\p p.p p:p p!p";
        String expectedOutput = "p\\\\p\\ p\\.p\\ p\\:p\\ p\\!p";

        // When
        String actualOutput = TokenQueue.escapeCssIdentifier(input);

        // Then
        assertEquals(expectedOutput, actualOutput);
    }
}
