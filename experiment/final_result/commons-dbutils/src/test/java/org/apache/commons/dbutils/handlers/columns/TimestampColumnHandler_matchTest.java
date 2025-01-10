
package org.apache.commons.dbutils.handlers.columns;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.sql.Timestamp;
import org.junit.Test;

public class TimestampColumnHandler_matchTest {

    @Test
    public void testMatchWithTimestampClass() {
        TimestampColumnHandler handler = new TimestampColumnHandler();
        assertTrue(handler.match(Timestamp.class));
    }

    @Test
    public void testMatchWithNonTimestampClass() {
        TimestampColumnHandler handler = new TimestampColumnHandler();
        assertFalse(handler.match(String.class));
    }
}
