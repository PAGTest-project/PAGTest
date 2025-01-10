
package com.binance.connector.client.utils;

import org.json.JSONObject;
import org.junit.Test;
import static org.junit.Assert.*;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import java.util.Arrays;

public class ParameterChecker_checkOneOfParametersRequiredTest {

    @Test
    public void testCheckOneOfParametersRequired_AllParametersMissing() {
        JSONObject params = new JSONObject();
        String[] parameters = {"param1", "param2"};
        String expectedMessage = "One of the following parameters is required: " + Arrays.toString(parameters);

        BinanceConnectorException exception = assertThrows(BinanceConnectorException.class, () -> {
            ParameterChecker.checkOneOfParametersRequired(params, parameters);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void testCheckOneOfParametersRequired_OneParameterPresent() {
        JSONObject params = new JSONObject();
        params.put("param1", "value1");
        String[] parameters = {"param1", "param2"};

        // No exception should be thrown
        ParameterChecker.checkOneOfParametersRequired(params, parameters);
    }

    @Test
    public void testCheckOneOfParametersRequired_NullParams() {
        String[] parameters = {"param1", "param2"};
        String expectedMessage = "One of the following parameters is required: " + Arrays.toString(parameters);

        BinanceConnectorException exception = assertThrows(BinanceConnectorException.class, () -> {
            ParameterChecker.checkOneOfParametersRequired(null, parameters);
        });

        assertEquals(expectedMessage, exception.getMessage());
    }
}
