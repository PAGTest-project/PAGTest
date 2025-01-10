
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import static org.junit.Assert.*;

public class DoubleColumnHandler_matchTest {

    @Test
    public void testMatchWithDoubleClass() {
        DoubleColumnHandler handler = new DoubleColumnHandler();
        assertTrue(handler.match(Double.class));
    }

    @Test
    public void testMatchWithDoubleType() {
        DoubleColumnHandler handler = new DoubleColumnHandler();
        assertTrue(handler.match(Double.TYPE));
    }

    @Test
    public void testMatchWithNonDoubleType() {
        DoubleColumnHandler handler = new DoubleColumnHandler();
        assertFalse(handler.match(String.class));
    }
}
