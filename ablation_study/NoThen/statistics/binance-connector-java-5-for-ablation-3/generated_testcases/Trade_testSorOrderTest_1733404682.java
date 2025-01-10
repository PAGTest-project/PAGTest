
package com.binance.connector.client.impl.spot;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.utils.ParameterChecker;
import com.binance.connector.client.utils.ProxyAuth;
import com.binance.connector.client.utils.RequestHandler;
import com.binance.connector.client.utils.signaturegenerator.HmacSignatureGenerator;

@RunWith(MockitoJUnitRunner.class)
public class Trade_testSorOrderTest {

    private Trade trade;
    private String baseUrl = "https://api.binance.com";
    private String apiKey = "testApiKey";
    private String secretKey = "testSecretKey";
    private boolean showLimitUsage = false;
    private ProxyAuth proxy = null;

    @Mock
    private RequestHandler requestHandler;

    @Before
    public void setUp() {
        trade = new Trade(baseUrl, apiKey, secretKey, showLimitUsage, proxy);
        trade.requestHandler = requestHandler;
    }

    @Test
    public void testSorOrder() {
        // Given
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");
        parameters.put("quantity", 1.0);

        // When
        trade.testSorOrder(parameters);

        // Then
        verify(requestHandler).sendSignedRequest(baseUrl, "/api/v3/sor/order/test", parameters, HttpMethod.POST, showLimitUsage);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSorOrderMissingQuantity() {
        // Given
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BNBUSDT");
        parameters.put("side", "BUY");
        parameters.put("type", "LIMIT");

        // When
        trade.testSorOrder(parameters);
    }
}
