
package com.binance.connector.client.utils;

import com.binance.connector.client.enums.HttpMethod;
import com.binance.connector.client.exceptions.BinanceConnectorException;
import com.binance.connector.client.utils.signaturegenerator.SignatureGenerator;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.Map;
import java.util.HashMap;

public class RequestHandler_sendApiRequestTest {

    private RequestHandler requestHandler;
    private ProxyAuth proxy;

    @Before
    public void setUp() {
        proxy = mock(ProxyAuth.class);
        requestHandler = new RequestHandler("validApiKey", null, proxy);
    }

    @Test
    public void testSendApiRequest_ValidApiKey() {
        String baseUrl = "https://api.binance.com";
        String urlPath = "/api/v3/order";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BTCUSDT");
        HttpMethod httpMethod = HttpMethod.GET;
        boolean showLimitUsage = true;

        String result = requestHandler.sendApiRequest(baseUrl, urlPath, parameters, httpMethod, showLimitUsage);

        assertNotNull(result);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testSendApiRequest_NullApiKey() {
        requestHandler = new RequestHandler(null, null, proxy);

        String baseUrl = "https://api.binance.com";
        String urlPath = "/api/v3/order";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BTCUSDT");
        HttpMethod httpMethod = HttpMethod.GET;
        boolean showLimitUsage = true;

        requestHandler.sendApiRequest(baseUrl, urlPath, parameters, httpMethod, showLimitUsage);
    }

    @Test(expected = BinanceConnectorException.class)
    public void testSendApiRequest_EmptyApiKey() {
        requestHandler = new RequestHandler("", null, proxy);

        String baseUrl = "https://api.binance.com";
        String urlPath = "/api/v3/order";
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("symbol", "BTCUSDT");
        HttpMethod httpMethod = HttpMethod.GET;
        boolean showLimitUsage = true;

        requestHandler.sendApiRequest(baseUrl, urlPath, parameters, httpMethod, showLimitUsage);
    }
}
