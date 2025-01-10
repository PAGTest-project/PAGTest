
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class ParseException_wrapTest {

    @Test
    public void testWrapWithUnsupportedOperationException() {
        final UnsupportedOperationException uoe = new UnsupportedOperationException("Unsupported operation");
        assertThrows(UnsupportedOperationException.class, () -> ParseException.wrap(uoe));
    }

    @Test
    public void testWrapWithParseException() {
        final ParseException pe = new ParseException("Parse error");
        assertEquals(pe, ParseException.wrap(pe));
    }

    @Test
    public void testWrapWithOtherException() {
        final IOException ioe = new IOException("IO error");
        final ParseException wrapped = ParseException.wrap(ioe);
        assertEquals(ioe, wrapped.getCause());
    }
}
