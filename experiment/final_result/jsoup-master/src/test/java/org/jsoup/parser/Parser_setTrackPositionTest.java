
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_setTrackPositionTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new HtmlTreeBuilder());
    }

    @Test
    public void testSetTrackPositionTrue() {
        Parser result = parser.setTrackPosition(true);
        assertTrue(parser.isTrackPosition());
        assertSame(parser, result);
    }

    @Test
    public void testSetTrackPositionFalse() {
        Parser result = parser.setTrackPosition(false);
        assertFalse(parser.isTrackPosition());
        assertSame(parser, result);
    }
}
