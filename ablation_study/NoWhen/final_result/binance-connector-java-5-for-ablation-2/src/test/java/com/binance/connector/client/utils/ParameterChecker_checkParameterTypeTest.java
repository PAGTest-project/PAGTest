
package com.binance.connector.client.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import com.binance.connector.client.exceptions.BinanceConnectorException;

public class ParameterChecker_checkParameterTypeTest {

    @Test
    public void testCheckParameterTypeValidString() {
        String parameter = "validString";
        ParameterChecker.checkParameterType(parameter, String.class, "testParameter");
    }

    @Test
    public void testCheckParameterTypeInvalidType() {
        Integer parameter = 123;
        BinanceConnectorException exception = assertThrows(BinanceConnectorException.class, () -> {
            ParameterChecker.checkParameterType(parameter, String.class, "testParameter");
        });
        assertEquals("\"testParameter\" must be of class java.lang.String type.", exception.getMessage());
    }

    @Test
    public void testCheckParameterTypeEmptyString() {
        String parameter = "";
        BinanceConnectorException exception = assertThrows(BinanceConnectorException.class, () -> {
            ParameterChecker.checkParameterType(parameter, String.class, "testParameter");
        });
        assertEquals("\"testParameter\" must not be empty.", exception.getMessage());
    }

    @Test
    public void testCheckParameterTypeValidInteger() {
        Integer parameter = 123;
        ParameterChecker.checkParameterType(parameter, Integer.class, "testParameter");
    }

    @Test
    public void testCheckParameterTypeInvalidStringType() {
        Integer parameter = 123;
        BinanceConnectorException exception = assertThrows(BinanceConnectorException.class, () -> {
            ParameterChecker.checkParameterType(parameter, String.class, "testParameter");
        });
        assertEquals("\"testParameter\" must be of class java.lang.String type.", exception.getMessage());
    }
}
