
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import java.util.UUID;

public class ParameterChecker_processIdTest {

    @Test
    public void testProcessIdWithValidInteger() {
        Integer id = 123;
        String name = "testId";
        assertEquals(id, ParameterChecker.processId(id, name));
    }

    @Test
    public void testProcessIdWithValidString() {
        String id = "456";
        String name = "testId";
        assertEquals(id, ParameterChecker.processId(id, name));
    }

    @Test
    public void testProcessIdWithNull() {
        String name = "testId";
        String result = (String) ParameterChecker.processId(null, name);
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
        String name = "testId";
        String result = (String) ParameterChecker.processId(id, name);
        assertNotNull(result);
        try {
            UUID.fromString(result);
        } catch (IllegalArgumentException e) {
            fail("Generated UUID is not valid");
        }
    }

    @Test
    public void testProcessIdWithInvalidType() {
        Double id = 123.45;
        String name = "testId";
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.processId(id, name));
    }
}
