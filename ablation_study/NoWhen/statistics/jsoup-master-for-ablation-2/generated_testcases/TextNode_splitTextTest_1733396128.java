
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
        TextNode tailNode = textNode.splitText(5);
        assertEquals("Hello", textNode.getWholeText());
        assertEquals(" World", tailNode.getWholeText());
    }

    @Test
    public void testSplitTextInvalidNegativeOffset() {
        assertThrows(IllegalArgumentException.class, () -> textNode.splitText(-1));
    }

    @Test
    public void testSplitTextInvalidOffsetGreaterThanLength() {
        assertThrows(IllegalArgumentException.class, () -> textNode.splitText(12));
    }

    @Test
    public void testSplitTextAtZeroOffset() {
        TextNode tailNode = textNode.splitText(0);
        assertEquals("", textNode.getWholeText());
        assertEquals("Hello World", tailNode.getWholeText());
    }

    @Test
    public void testSplitTextAtFullLengthOffset() {
        TextNode tailNode = textNode.splitText(11);
        assertEquals("Hello World", textNode.getWholeText());
        assertEquals("", tailNode.getWholeText());
    }
}
