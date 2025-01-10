
package org.apache.commons.dbutils.handlers.properties;

import org.junit.Test;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import static org.junit.Assert.assertEquals;

public class DatePropertyHandler_applyTest {

    private DatePropertyHandler handler = new DatePropertyHandler();

    @Test
    public void testApplyWithJavaSqlDate() {
        Date dateValue = new Date(System.currentTimeMillis());
        Object result = handler.apply(Date.class, dateValue);
        assertEquals(java.sql.Date.class, result.getClass());
    }

    @Test
    public void testApplyWithJavaSqlTime() {
        Date dateValue = new Date(System.currentTimeMillis());
        Object result = handler.apply(Time.class, dateValue);
        assertEquals(java.sql.Time.class, result.getClass());
    }

    @Test
    public void testApplyWithJavaSqlTimestamp() {
        Date dateValue = new Date(System.currentTimeMillis());
        Object result = handler.apply(Timestamp.class, dateValue);
        assertEquals(Timestamp.class, result.getClass());
    }

    @Test
    public void testApplyWithDefault() {
        Date dateValue = new Date(System.currentTimeMillis());
        Object result = handler.apply(String.class, dateValue);
        assertEquals(Date.class, result.getClass());
    }
}
