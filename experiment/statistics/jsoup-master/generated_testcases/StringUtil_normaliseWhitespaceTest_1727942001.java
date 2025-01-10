
package org.jsoup.internal;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtil_normaliseWhitespaceTest {

    @Test
    public void testNormaliseWhitespace() {
        // Given
        String input = "  Hello   World  \n\t";
        String expected = "Hello World";

        // When
        String result = StringUtil.normaliseWhitespace(input);

        // Then
        assertEquals(expected, result);
    }
}
