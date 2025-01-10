
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringColumnHandler_matchTest {

    @Test
    public void testMatchWithStringClass() {
        StringColumnHandler handler = new StringColumnHandler();
        assertTrue(handler.match(String.class));
    }

    @Test
    public void testMatchWithNonStringClass() {
        StringColumnHandler handler = new StringColumnHandler();
        assertFalse(handler.match(Integer.class));
    }
}
