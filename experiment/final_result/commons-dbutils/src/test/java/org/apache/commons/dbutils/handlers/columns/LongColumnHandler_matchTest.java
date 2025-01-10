
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import static org.junit.Assert.*;

public class LongColumnHandler_matchTest {

    @Test
    public void testMatchWithLongType() {
        LongColumnHandler handler = new LongColumnHandler();
        assertTrue(handler.match(Long.TYPE));
    }

    @Test
    public void testMatchWithLongClass() {
        LongColumnHandler handler = new LongColumnHandler();
        assertTrue(handler.match(Long.class));
    }

    @Test
    public void testMatchWithNonLongType() {
        LongColumnHandler handler = new LongColumnHandler();
        assertFalse(handler.match(Integer.class));
    }
}
