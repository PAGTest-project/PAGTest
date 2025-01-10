
package com.binance.connector.client.utils;

import static org.junit.Assert.assertThrows;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.binance.connector.client.exceptions.BinanceConnectorException;

public class ParameterChecker_checkParameterTest {

    @Test
    public void testCheckParameter_ValidParameter() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param1", "value1");
        ParameterChecker.checkParameter(parameters, "param1", String.class);
    }

    @Test
    public void testCheckParameter_MissingParameter() {
        Map<String, Object> parameters = new HashMap<>();
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkParameter(parameters, "missingParam", String.class));
    }

    @Test
    public void testCheckParameter_WrongType() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("param1", 123);
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkParameter(parameters, "param1", String.class));
    }
}
