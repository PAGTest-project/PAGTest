
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import static org.junit.Assert.*;

public class FloatColumnHandler_matchTest {

    @Test
    public void testMatchWithFloatClass() {
        FloatColumnHandler handler = new FloatColumnHandler();
        assertTrue(handler.match(Float.class));
    }

    @Test
    public void testMatchWithFloatType() {
        FloatColumnHandler handler = new FloatColumnHandler();
        assertTrue(handler.match(Float.TYPE));
    }

    @Test
    public void testMatchWithNonFloatClass() {
        FloatColumnHandler handler = new FloatColumnHandler();
        assertFalse(handler.match(Integer.class));
    }
}
