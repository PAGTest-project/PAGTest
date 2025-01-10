
package com.binance.connector.client.utils;

import static org.junit.Assert.assertThrows;
import org.json.JSONObject;
import org.junit.Test;
import com.binance.connector.client.exceptions.BinanceConnectorException;

public class ParameterChecker_checkOnlyOneOfParametersTest {

    @Test
    public void testCheckOnlyOneOfParameters_OnlyOneParameterPresent() {
        JSONObject params = new JSONObject();
        params.put("param1", "value1");
        ParameterChecker.checkOnlyOneOfParameters(params, "param1", "param2", "param3");
    }

    @Test
    public void testCheckOnlyOneOfParameters_MoreThanOneParameterPresent() {
        JSONObject params = new JSONObject();
        params.put("param1", "value1");
        params.put("param2", "value2");
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkOnlyOneOfParameters(params, "param1", "param2", "param3"));
    }

    @Test
    public void testCheckOnlyOneOfParameters_NoParametersPresent() {
        JSONObject params = new JSONObject();
        ParameterChecker.checkOnlyOneOfParameters(params, "param1", "param2", "param3");
    }

    @Test
    public void testCheckOnlyOneOfParameters_NullParams() {
        JSONObject params = null;
        ParameterChecker.checkOnlyOneOfParameters(params, "param1", "param2", "param3");
    }
}
