
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import com.binance.connector.client.exceptions.BinanceConnectorException;

public class ParameterChecker_processIdTest {

    @Test
    public void testProcessId_ValidInteger() {
        Object result = ParameterChecker.processId(123, "testId");
        assertEquals(123, result);
    }

    @Test
    public void testProcessId_ValidString() {
        Object result = ParameterChecker.processId("abc", "testId");
        assertEquals("abc", result);
    }

    @Test
    public void testProcessId_NullId() {
        Object result = ParameterChecker.processId(null, "testId");
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test
    public void testProcessId_EmptyString() {
        Object result = ParameterChecker.processId("", "testId");
        assertNotNull(result);
        assertTrue(result instanceof String);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testProcessId_InvalidType() {
        ParameterChecker.processId(new Object(), "testId");
    }
}
