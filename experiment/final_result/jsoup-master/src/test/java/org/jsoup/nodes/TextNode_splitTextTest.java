
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextNode_splitTextTest {
    private TextNode textNode;

    @BeforeEach
    public void setUp() {
        textNode = new TextNode("Hello World");
    }

    @Test
    public void testSplitTextValidOffset() {
        TextNode result = textNode.splitText(5);
        assertEquals("Hello", textNode.getWholeText());
        assertEquals(" World", result.getWholeText());
    }

    @Test
    public void testSplitTextNegativeOffset() {
        assertThrows(IllegalArgumentException.class, () -> textNode.splitText(-1));
    }

    @Test
    public void testSplitTextOffsetGreaterThanLength() {
        assertThrows(IllegalArgumentException.class, () -> textNode.splitText(12));
    }
}
