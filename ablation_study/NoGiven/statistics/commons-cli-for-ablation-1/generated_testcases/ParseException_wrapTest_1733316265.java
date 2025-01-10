
package org.apache.commons.cli;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ParseException_wrapTest {

    @Test
    public void testWrapWithUnsupportedOperationException() {
        UnsupportedOperationException unsupportedOperationException = new UnsupportedOperationException("Test exception");
        assertThrows(UnsupportedOperationException.class, () -> ParseException.wrap(unsupportedOperationException));
    }

    @Test
    public void testWrapWithParseException() {
        ParseException parseException = new ParseException("Test exception");
        ParseException result = ParseException.wrap(parseException);
        assertSame(parseException, result);
    }

    @Test
    public void testWrapWithGenericException() {
        Exception genericException = new Exception("Test exception");
        ParseException result = ParseException.wrap(genericException);
        assertNotNull(result);
        assertEquals(genericException, result.getCause());
    }
}
