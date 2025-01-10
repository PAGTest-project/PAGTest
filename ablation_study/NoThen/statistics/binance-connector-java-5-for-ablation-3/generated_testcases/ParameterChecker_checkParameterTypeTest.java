
package com.binance.connector.client.utils;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import com.binance.connector.client.exceptions.BinanceConnectorException;

public class ParameterChecker_checkParameterTypeTest {

    @Test
    public void testCheckParameterTypeValidString() {
        String validString = "testString";
        ParameterChecker.checkParameterType(validString, String.class, "testParameter");
    }

    @Test
    public void testCheckParameterTypeValidInteger() {
        Integer validInteger = 123;
        ParameterChecker.checkParameterType(validInteger, Integer.class, "testParameter");
    }

    @Test
    public void testCheckParameterTypeInvalidType() {
        Integer invalidType = 123;
        assertThrows(BinanceConnectorException.class, () -> {
            ParameterChecker.checkParameterType(invalidType, String.class, "testParameter");
        });
    }

    @Test
    public void testCheckParameterTypeEmptyString() {
        String emptyString = "";
        assertThrows(BinanceConnectorException.class, () -> {
            ParameterChecker.checkParameterType(emptyString, String.class, "testParameter");
        });
    }
}
