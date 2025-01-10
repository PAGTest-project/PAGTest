
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
        ParseException result = ParseException.wrap(parseException);
        assertSame(parseException, result);
    }

    @Test
    void testWrapWithOtherException() {
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Test exception");
        ParseException result = ParseException.wrap(illegalArgumentException);
        assertNotNull(result);
        assertEquals(illegalArgumentException, result.getCause());
    }
}
