
package org.apache.commons.dbutils.handlers.properties;

import org.junit.Test;
import java.sql.Timestamp;
import java.util.Date;
import static org.junit.Assert.*;

public class DatePropertyHandler_matchTest {

    private DatePropertyHandler handler = new DatePropertyHandler();

    @Test
    public void testMatch_JavaSqlDate() {
        assertTrue(handler.match(java.sql.Date.class, new Date()));
    }

    @Test
    public void testMatch_JavaSqlTime() {
        assertTrue(handler.match(java.sql.Time.class, new Date()));
    }

    @Test
    public void testMatch_JavaSqlTimestamp_NotTimestampInstance() {
        assertTrue(handler.match(Timestamp.class, new Date()));
    }

    @Test
    public void testMatch_JavaSqlTimestamp_IsTimestampInstance() {
        assertFalse(handler.match(Timestamp.class, new Timestamp(System.currentTimeMillis())));
    }

    @Test
    public void testMatch_NonDateValue() {
        assertFalse(handler.match(Date.class, "not a date"));
    }
}
