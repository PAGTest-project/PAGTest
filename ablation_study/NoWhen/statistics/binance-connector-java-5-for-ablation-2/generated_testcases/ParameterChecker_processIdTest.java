
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import java.util.UUID;

public class ParameterChecker_processIdTest {

    @Test
    public void testProcessIdWithValidInteger() {
        Integer id = 123;
        assertEquals(id, ParameterChecker.processId(id, "testId"));
    }

    @Test
    public void testProcessIdWithValidString() {
        String id = "456";
        assertEquals(id, ParameterChecker.processId(id, "testId"));
    }

    @Test
    public void testProcessIdWithNull() {
        Object id = null;
        String result = (String) ParameterChecker.processId(id, "testId");
        assertNotNull(result);
        try {
            UUID.fromString(result);
        } catch (IllegalArgumentException e) {
            fail("Generated UUID is not valid");
        }
    }

    @Test
    public void testProcessIdWithEmptyString() {
        String id = "";
        String result = (String) ParameterChecker.processId(id, "testId");
        assertNotNull(result);
        try {
            UUID.fromString(result);
        } catch (IllegalArgumentException e) {
            fail("Generated UUID is not valid");
        }
    }

    @Test
    public void testProcessIdWithInvalidType() {
        Object id = new Object();
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.processId(id, "testId"));
    }
}
