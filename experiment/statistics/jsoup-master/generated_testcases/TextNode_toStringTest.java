
package org.jsoup.nodes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextNode_toStringTest {
    private TextNode textNode;

    @BeforeEach
    public void setUp() {
        textNode = new TextNode("Hello &<> Å å π 新 there ¾ © » ' \"");
    }

    @Test
    public void testToString() {
        String expectedHtml = "Hello &amp;&lt;&gt; Å å π 新 there ¾ © » ' \"";
        assertEquals(expectedHtml, textNode.toString());
    }
}
