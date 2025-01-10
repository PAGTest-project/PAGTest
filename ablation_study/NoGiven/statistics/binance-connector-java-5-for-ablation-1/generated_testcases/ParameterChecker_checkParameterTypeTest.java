
package com.binance.connector.client.utils;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import com.binance.connector.client.exceptions.BinanceConnectorException;

public class ParameterChecker_checkParameterTypeTest {

    @Test
    public void testCheckParameterTypeValid() {
        ParameterChecker.checkParameterType("validString", String.class, "testParameter");
    }

    @Test
    public void testCheckParameterTypeInvalid() {
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkParameterType(123, String.class, "testParameter"));
    }

    @Test
    public void testCheckParameterTypeEmptyString() {
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkParameterType("", String.class, "testParameter"));
    }
}
