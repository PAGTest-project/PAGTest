
package com.binance.connector.client.utils;

import static org.junit.Assert.assertThrows;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import com.binance.connector.client.exceptions.BinanceConnectorException;

public class ParameterChecker_checkRequiredParameterTest {

    @Test
    public void testCheckRequiredParameter_ParameterPresent() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("requiredParam", "value");
        ParameterChecker.checkRequiredParameter(parameters, "requiredParam");
    }

    @Test
    public void testCheckRequiredParameter_ParameterMissing() {
        Map<String, Object> parameters = new HashMap<>();
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkRequiredParameter(parameters, "missingParam"));
    }
}
