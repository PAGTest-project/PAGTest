
package com.binance.connector.client.utils;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import java.util.HashMap;
import java.util.Map;

public class ParameterChecker_checkRequiredParameterTest {

    @Test
    public void testCheckRequiredParameterPresent() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("requiredParam", "value");
        ParameterChecker.checkRequiredParameter(parameters, "requiredParam");
    }

    @Test
    public void testCheckRequiredParameterMissing() {
        Map<String, Object> parameters = new HashMap<>();
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkRequiredParameter(parameters, "missingParam"));
    }
}
