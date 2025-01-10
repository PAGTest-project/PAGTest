
package org.apache.commons.dbutils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.Duration;
import org.junit.Test;

public class StatementConfiguration_getQueryTimeoutTest {

    @Test
    public void testGetQueryTimeoutWithNonNullDuration() {
        StatementConfiguration config = new StatementConfiguration.Builder()
                .queryTimeout(Duration.ofSeconds(10))
                .build();
        assertEquals(Integer.valueOf(10), config.getQueryTimeout());
    }

    @Test
    public void testGetQueryTimeoutWithNullDuration() {
        StatementConfiguration config = new StatementConfiguration.Builder()
                .build();
        assertNull(config.getQueryTimeout());
    }
}
