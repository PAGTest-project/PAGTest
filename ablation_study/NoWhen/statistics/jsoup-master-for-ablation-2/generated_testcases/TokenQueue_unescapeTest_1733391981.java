
package org.jsoup.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TokenQueue_unescapeTest {

    @Test
    public void testUnescapeSingleEscape() {
        assertEquals("test", TokenQueue.unescape("test"));
    }

    @Test
    public void testUnescapeDoubleEscape() {
        assertEquals("test\\", TokenQueue.unescape("test\\\\"));
    }

    @Test
    public void testUnescapeMixedEscapes() {
        assertEquals("test\\test", TokenQueue.unescape("test\\\\test"));
    }

    @Test
    public void testUnescapeNoEscapes() {
        assertEquals("noescapeshere", TokenQueue.unescape("noescapeshere"));
    }

    @Test
    public void testUnescapeEmptyString() {
        assertEquals("", TokenQueue.unescape(""));
    }

    @Test
    public void testUnescapeMultipleEscapes() {
        assertEquals("test\\\\test", TokenQueue.unescape("test\\\\\\\\test"));
    }

    @Test
    public void testUnescapeEscapeAtEnd() {
        assertEquals("test\\", TokenQueue.unescape("test\\\\"));
    }

    @Test
    public void testUnescapeEscapeAtStart() {
        assertEquals("\\test", TokenQueue.unescape("\\\\test"));
    }

    @Test
    public void testUnescapeEscapeInMiddle() {
        assertEquals("te\\st", TokenQueue.unescape("te\\\\st"));
    }

    @Test
    public void testUnescapeMultipleEscapesInMiddle() {
        assertEquals("te\\\\st", TokenQueue.unescape("te\\\\\\\\st"));
    }
}
