
package org.apache.commons.dbutils.handlers.columns;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ByteColumnHandler_matchTest {
    private ByteColumnHandler byteColumnHandler;

    @Before
    public void setUp() {
        byteColumnHandler = new ByteColumnHandler();
    }

    @Test
    public void testMatchWithByteType() {
        assertTrue(byteColumnHandler.match(Byte.TYPE));
    }

    @Test
    public void testMatchWithByteClass() {
        assertTrue(byteColumnHandler.match(Byte.class));
    }

    @Test
    public void testMatchWithNonByteType() {
        assertFalse(byteColumnHandler.match(Integer.TYPE));
    }
}
