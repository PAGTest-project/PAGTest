
package org.jsoup.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Parser_setTrackErrorsTest {
    private Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser(new HtmlTreeBuilder());
    }

    @Test
    public void testSetTrackErrorsWithPositiveMaxErrors() {
        parser.setTrackErrors(5);
        assertTrue(parser.isTrackErrors());
        assertEquals(5, parser.getErrors().getMaxSize());
    }

    @Test
    public void testSetTrackErrorsWithZeroMaxErrors() {
        parser.setTrackErrors(0);
        assertFalse(parser.isTrackErrors());
        assertEquals(0, parser.getErrors().getMaxSize());
    }
}
