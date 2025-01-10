
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.RequestHandler;

public class Trade_commissionTest {

    private Trade trade;
    private String baseUrl = "https://api.binance.com";
    private String apiKey = "testApiKey";
    private String secretKey = "testSecretKey";
    private boolean showLimitUsage = false;

    @Mock
    private RequestHandler requestHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        trade = new Trade(baseUrl, apiKey, secretKey, showLimitUsage, null);
        trade.requestHandler = requestHandler; // Directly assign the mock to the requestHandler field
    }

    @Test
    public void testCommission() {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BNBUSDT");

        String expectedResponse = "{\"commissionRates\":{\"maker\":0.001,\"taker\":0.001}}";
        when(requestHandler.sendSignedRequest(baseUrl, "/api/v3/account/commission", parameters, HttpMethod.GET, showLimitUsage))
            .thenReturn(expectedResponse);

        String result = trade.commission(parameters);
        assertEquals(expectedResponse, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCommissionMissingSymbol() {
        Map<String, Object> parameters = new HashMap<>();
        trade.commission(parameters);
    }
}
