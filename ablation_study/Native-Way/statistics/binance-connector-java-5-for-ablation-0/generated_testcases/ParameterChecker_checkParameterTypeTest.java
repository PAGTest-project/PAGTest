
package com.binance.connector.client.utils;

import static org.junit.Assert.assertThrows;
import org.junit.Test;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import java.util.HashMap;
import java.util.Map;

public class ParameterChecker_checkParameterTypeTest {

    @Test
    public void testCheckParameterTypeValid() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("validKey", "validValue");
        ParameterChecker.checkParameterType(parameters.get("validKey"), String.class, "validKey");
    }

    @Test
    public void testCheckParameterTypeInvalid() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("invalidKey", 123);
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkParameterType(parameters.get("invalidKey"), String.class, "invalidKey"));
    }

    @Test
    public void testCheckParameterTypeEmptyString() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("emptyKey", "");
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkParameterType(parameters.get("emptyKey"), String.class, "emptyKey"));
    }
}
