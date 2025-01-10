
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import java.util.UUID;

public class ParameterChecker_processIdTest {

    @Test
    public void testProcessIdWithInteger() {
        Object result = ParameterChecker.processId(123, "testId");
        assertEquals(123, result);
    }

    @Test
    public void testProcessIdWithNonEmptyString() {
        Object result = ParameterChecker.processId("abc", "testId");
        assertEquals("abc", result);
    }

    @Test
    public void testProcessIdWithEmptyString() {
        Object result = ParameterChecker.processId("", "testId");
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertNotEquals("", result);
    }

    @Test
    public void testProcessIdWithNull() {
        Object result = ParameterChecker.processId(null, "testId");
        assertNotNull(result);
        assertTrue(result instanceof String);
        assertNotEquals("", result);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testProcessIdWithInvalidType() {
        ParameterChecker.processId(new Object(), "testId");
    }
}
