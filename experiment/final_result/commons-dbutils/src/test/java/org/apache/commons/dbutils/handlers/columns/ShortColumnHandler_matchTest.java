
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Test;
import static org.junit.Assert.*;

public class ShortColumnHandler_matchTest {

    @Test
    public void testMatchWithShortType() {
        ShortColumnHandler handler = new ShortColumnHandler();
        assertTrue(handler.match(Short.TYPE));
    }

    @Test
    public void testMatchWithShortClass() {
        ShortColumnHandler handler = new ShortColumnHandler();
        assertTrue(handler.match(Short.class));
    }

    @Test
    public void testMatchWithNonShortType() {
        ShortColumnHandler handler = new ShortColumnHandler();
        assertFalse(handler.match(Integer.TYPE));
    }
}
