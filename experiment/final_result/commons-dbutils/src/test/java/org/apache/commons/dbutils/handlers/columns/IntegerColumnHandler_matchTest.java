
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import static org.junit.Assert.*;

public class IntegerColumnHandler_matchTest {

    @Test
    public void testMatchWithIntegerClass() {
        IntegerColumnHandler handler = new IntegerColumnHandler();
        assertTrue(handler.match(Integer.class));
    }

    @Test
    public void testMatchWithIntegerType() {
        IntegerColumnHandler handler = new IntegerColumnHandler();
        assertTrue(handler.match(Integer.TYPE));
    }

    @Test
    public void testMatchWithNonIntegerType() {
        IntegerColumnHandler handler = new IntegerColumnHandler();
        assertFalse(handler.match(String.class));
    }
}
