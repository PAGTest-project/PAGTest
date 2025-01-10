
package com.binance.connector.client.utils;

import static org.junit.Assert.assertThrows;
import org.json.JSONObject;
import org.junit.Test;
import com.binance.connector.client.exceptions.BinanceConnectorException;

public class ParameterChecker_checkOneOfParametersRequiredTest {

    @Test
    public void testCheckOneOfParametersRequired_AllParametersMissing() {
        JSONObject params = new JSONObject();
        String[] parameters = {"param1", "param2", "param3"};
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkOneOfParametersRequired(params, parameters));
    }

    @Test
    public void testCheckOneOfParametersRequired_OneParameterPresent() {
        JSONObject params = new JSONObject();
        params.put("param2", "value2");
        String[] parameters = {"param1", "param2", "param3"};
        ParameterChecker.checkOneOfParametersRequired(params, parameters); // Should not throw exception
    }

    @Test
    public void testCheckOneOfParametersRequired_AllParametersPresent() {
        JSONObject params = new JSONObject();
        params.put("param1", "value1");
        params.put("param2", "value2");
        params.put("param3", "value3");
        String[] parameters = {"param1", "param2", "param3"};
        ParameterChecker.checkOneOfParametersRequired(params, parameters); // Should not throw exception
    }

    @Test
    public void testCheckOneOfParametersRequired_NullParams() {
        String[] parameters = {"param1", "param2", "param3"};
        assertThrows(BinanceConnectorException.class, () -> ParameterChecker.checkOneOfParametersRequired(null, parameters));
    }
}
