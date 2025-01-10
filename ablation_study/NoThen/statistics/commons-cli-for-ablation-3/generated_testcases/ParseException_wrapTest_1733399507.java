
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ParseException_wrapTest {

    @Test
    public void testWrapWithUnsupportedOperationException() {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Unsupported operation");
        assertThrows(UnsupportedOperationException.class, () -> ParseException.wrap(unsupportedOperationException));
    }

    @Test
    public void testWrapWithParseException() {
        ParseException parseException = new ParseException("Parse error");
        assertEquals(parseException, ParseException.wrap(parseException));
    }

    @Test
    public void testWrapWithOtherException() {
        IOException ioException = new IOException("IO error");
        ParseException wrappedException = ParseException.wrap(ioException);
        assertEquals(ioException, wrappedException.getCause());
    }
}
