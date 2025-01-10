
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import static org.junit.Assert.*;

public class BooleanColumnHandler_matchTest {

    @Test
    public void testMatchWithBooleanType() {
        BooleanColumnHandler handler = new BooleanColumnHandler();
        assertTrue(handler.match(Boolean.TYPE));
        assertTrue(handler.match(Boolean.class));
    }

    @Test
    public void testMatchWithNonBooleanType() {
        BooleanColumnHandler handler = new BooleanColumnHandler();
        assertFalse(handler.match(Integer.TYPE));
        assertFalse(handler.match(String.class));
    }
}
