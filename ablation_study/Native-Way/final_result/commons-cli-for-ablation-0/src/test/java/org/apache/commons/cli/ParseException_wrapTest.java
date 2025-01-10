
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ParseException_wrapTest {

    @Test
    void testWrapWithUnsupportedOperationException() {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Test exception");
        assertThrows(UnsupportedOperationException.class, () -> ParseException.wrap(unsupportedOperationException));
    }

    @Test
    void testWrapWithParseException() {
        ParseException parseException = new ParseException("Test exception");
        assertEquals(parseException, ParseException.wrap(parseException));
    }

    @Test
    void testWrapWithGenericException() {
        Exception genericException = new Exception("Test exception");
        ParseException wrappedException = ParseException.wrap(genericException);
        assertEquals(genericException, wrappedException.getCause());
    }
}
