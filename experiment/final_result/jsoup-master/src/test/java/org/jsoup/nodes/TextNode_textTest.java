
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TextNode_textTest {
    private TextNode textNode;

    @BeforeEach
    public void setUp() {
        textNode = new TextNode("");
    }

    @Test
    public void testText_SetText_ReturnsSameInstance() {
        TextNode result = textNode.text("Hello, World!");
        assertSame(textNode, result);
    }

    @Test
    public void testText_SetText_UpdatesCoreValue() {
        textNode.text("Hello, World!");
        assertEquals("Hello, World!", textNode.getWholeText());
    }
}
