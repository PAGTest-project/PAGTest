
package org.jsoup.nodes;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextNode_createFromEncodedTest {

    @Test
    public void testCreateFromEncoded() {
        // Given
        String encodedText = "&lt;p&gt;Hello, World!&lt;/p&gt;";

        // When
        TextNode textNode = TextNode.createFromEncoded(encodedText);

        // Then
        assertEquals("<p>Hello, World!</p>", textNode.getWholeText());
    }
}
