
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import java.util.UUID;

public class ParameterChecker_processIdTest {

    @Test
    public void testProcessIdWithInteger() {
        Integer id = 123;
        assertEquals(id, ParameterChecker.processId(id, "id"));
    }

    @Test
    public void testProcessIdWithString() {
        String id = "abc";
        assertEquals(id, ParameterChecker.processId(id, "id"));
    }

    @Test
    public void testProcessIdWithNull() {
        Object id = null;
        String result = (String) ParameterChecker.processId(id, "id");
        assertNotNull(result);
        try {
            UUID.fromString(result);
        } catch (IllegalArgumentException e) {
            fail("Returned value is not a valid UUID");
        }
    }

    @Test
    public void testProcessIdWithEmptyString() {
        String id = "";
        String result = (String) ParameterChecker.processId(id, "id");
        assertNotNull(result);
        try {
            UUID.fromString(result);
        } catch (IllegalArgumentException e) {
            fail("Returned value is not a valid UUID");
        }
    }

    @Test
    public void testProcessIdWithInvalidType() {
        Object id = new Object();
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.processId(id, "id"));
    }
}
